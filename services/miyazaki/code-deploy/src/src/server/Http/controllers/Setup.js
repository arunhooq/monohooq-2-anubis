const SHA256 = require('crypto-js/hmac-sha256');
const Base64 = require('crypto-js/enc-base64');
const { unset } = require('lodash');
const redis = require('../../../connections/redis');
const UserHelper = require('../../Helpers/User');
const WebError = require('../../Errors/WebError');
const { createCookiesOpts } = require('../../Util/Cookies');
const { isValidUrl } = require('../../Util/Url');
const { deepCompile } = require('asura-helpers/value');
const { getClientGeo } = require('../../Helpers/IpAddress');
const {
  PRIMARY_ID_NOT_FOUND,
  FAILED_SIGNIN
} = require('../../../common/ErrorCodes');
const SessionKey = require('../../Util/SessionKey');
const get = require('lodash.get');
const { isOverrideEVSignin } = require('../../Helpers/EVWebview');

const EXPIRE_7_DAYS = 604800;

/**
 * @param {Object} ctx
 * @param {Function} next
 */
async function setup(ctx, next) {
  const { session, partnerId, isVisitor } = ctx.state;
  const device = get(ctx, 'state.device', { os: 'webClient' });
  const overrideEVWebviewAuthConfig = get(
    ctx,
    'state.partnerConfiguration.CustomConfig.actions.setup.overrideEVWebviewAuth',
    false
  );
  const redirectUrl = setRedirectUrl(ctx);
  const geo = getClientGeo(ctx);

  if (
    (session &&
      !isVisitor &&
      !isOverrideEVSignin(overrideEVWebviewAuthConfig, device)) ||
    (session && session.isEvWebviewSignin)
  ) {
    const userDetail = await UserHelper.getUserDetail(ctx, session.session);

    const userSubscription = await getUserSubscription(ctx);
    const subscription =
      userSubscription.data.length !== 0 ? userSubscription.data : [];

    const payload = {
      ...session,
      user: userDetail,
      subscription
    };

    const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
    await redis.setAsync(key, JSON.stringify(payload), 'EX', EXPIRE_7_DAYS);
    return ctx.redirectTo(redirectUrl);
  }

  if (ctx.state[partnerId]) {
    ctx.state.isVisitor = false;
  }

  await initSession(ctx, redirectUrl, geo);
}

async function verifyEvergent(ctx, next) {
  const { authCode, status, activatedWith, evWebviewSignin } = ctx.query;

  // TODO: check HMAC EV signature
  try {
    if (status === 'success') {
      let redirectUrl = setRedirectUrl(ctx);
      const activation = await activateClient(ctx);
      const auth = await getOAuthAccessToken(ctx, authCode);
      const geo = getClientGeo(ctx);
      const userDetail = await UserHelper.getUserDetail(ctx, auth.data);
      const userSubscription = await getUserSubscription(ctx);
      const subscription =
        userSubscription.data.length !== 0 ? userSubscription.data : [];
      ctx.state.isVisitor = false;
      const session = auth.data;
      const webviewSignin = evWebviewSignin === 'false' ? false : true;

      ctx.state.sessionId = SessionKey.create(
        userDetail.cpCustomerId,
        ctx.state.env.KEY
      );

      await setSession(ctx, {
        session,
        userDetail,
        subscription,
        activation,
        geo,
        webviewSignin
      });

      const opts = createCookiesOpts(ctx);
      const {
        cookieKey
      } = ctx.state.partnerConfiguration.CustomConfig.middlewares.session;
      ctx.cookies.set(cookieKey, ctx.state.sessionId, opts);

      if (activatedWith) {
        redirectUrl = redirectUrl.includes('?')
          ? `${redirectUrl}&activatedWith=${activatedWith}`
          : `${redirectUrl}?activatedWith=${activatedWith}`;
      }

      return ctx.redirectTo(redirectUrl);
    }

    throw new WebError(
      FAILED_SIGNIN.message,
      FAILED_SIGNIN.statusCode,
      FAILED_SIGNIN.errorCode,
      FAILED_SIGNIN.message,
      false,
      false
    );
  } catch (err) {
    throw err;
  }
}

async function getOAuthAccessToken(ctx, authCode) {
  try {
    const opts = {
      path: '/auth/getOAuthAccessToken',
      payload: {
        channelPartnerID: 'HAWK',
        isGlobalSearch: true,
        authCode
      },
      customApiVersion: '1.0'
    };
    const response = await ctx.state.Api.post(opts);
    ctx.state.Api.setSessionToken(response.data.accessToken);
    ctx.state.Api.setRefreshToken(response.data.refreshToken);

    return response;
  } catch (err) {
    throw err;
  }
}

async function initSession(ctx, redirectUrl, geo) {
  try {
    const activation = await activateClient(ctx);
    const { session, visitorToken } = await getUserSession(
      ctx,
      activation,
      geo
    );
    const userDetail = await UserHelper.getUserDetail(
      ctx,
      session,
      visitorToken
    );

    ctx.state.isVisitor = visitorToken ? true : false;

    let subscription = [];
    if (!ctx.state.isVisitor) {
      const userSubscription = await getUserSubscription(ctx);
      subscription = userSubscription.data.length ? userSubscription.data : [];
    }

    await setSession(ctx, {
      session,
      userDetail,
      subscription,
      activation,
      geo,
      webviewSignin: false
    });

    const opts = createCookiesOpts(ctx);

    const {
      cookieKey
    } = ctx.state.partnerConfiguration.CustomConfig.middlewares.session;
    ctx.cookies.set(cookieKey, ctx.state.sessionId, opts);

    logSetup(ctx, geo);

    ctx.redirectTo(redirectUrl);
  } catch (err) {
    throw err;
  }
}

async function getUserSession(ctx, activation, geo) {
  let visitorToken;
  let session;
  const device = get(ctx, 'state.device', { os: 'webClient' });
  const overrideEVWebviewAuthConfig = get(
    ctx,
    'state.partnerConfiguration.CustomConfig.actions.setup.overrideEVWebviewAuth',
    false
  );

  // check if it is enable and device is match
  if (
    ctx.state.isVisitor ||
    isOverrideEVSignin(overrideEVWebviewAuthConfig, device)
  ) {
    visitorToken = await getVisitorToken(ctx, activation.device_signature);
    session = {
      accessToken: visitorToken.accessToken,
      refreshToken: visitorToken.refreshToken
    };
    return { session, visitorToken };
  }

  try {
    const signin = await signInUser(ctx, geo);
    session = signin.data;
  } catch (err) {
    const signin = await createAndSignInUser(ctx, geo);
    session = signin.data.session;
  }

  return { session, visitorToken };
}

async function activateClient(ctx) {
  const { source, platform, os, version } = ctx.userAgent;
  const { serialNo } = ctx.state.device;
  const opts = {
    path: '/client/activate',
    payload: {
      device_id: serialNo,
      useragent: source,
      os,
      version,
      model: platform
    }
  };

  try {
    const response = await ctx.state.Api.post(opts);
    const { device_signature } = response;
    ctx.state.Api.setDeviceSignature(device_signature);
    return response;
  } catch (err) {
    throw err;
  }
}

async function signInUser(ctx, geo) {
  try {
    const { device } = ctx.state;
    const { HMACKeys } = ctx.state.partnerConfiguration;
    const primaryId = getPrimaryId(ctx);

    const opts = {
      path: '/user/signin',
      payload: {
        data: {
          ...primaryId,
          ipAddress: geo.ip,
          device: {
            brand: device.brand,
            modelNo: device.modelNo,
            name: device.name,
            os: device.os,
            osVersion: device.osVersion,
            serialNo: device.serialNo,
            type: device.type
          }
        },
        meta: {
          hmac: buildHmac(HMACKeys, primaryId)
        }
      }
    };
    const response = await ctx.state.Api.post(opts);
    ctx.state.Api.setSessionToken(response.data.accessToken);
    ctx.state.Api.setRefreshToken(response.data.refreshToken);

    return response;
  } catch (err) {
    throw err;
  }
}

async function createAndSignInUser(ctx, geo) {
  try {
    const { device } = ctx.state;
    const { HMACKeys } = ctx.state.partnerConfiguration;
    const primaryId = getPrimaryId(ctx);
    const opts = {
      path: '/user',
      payload: {
        data: {
          ...primaryId,
          ipAddress: geo.ip,
          country: geo.country
        },
        meta: {
          withSignIn: {
            enabled: true,
            hmac: buildHmac(HMACKeys, primaryId),
            device: {
              serialNo: device.serialNo,
              name: device.name,
              type: device.type,
              modelNo: device.modelNo,
              brand: device.brand,
              os: device.os,
              osVersion: device.osVersion
            }
          }
        }
      }
    };
    const response = await ctx.state.Api.post(opts);
    ctx.state.Api.setSessionToken(response.data.session.accessToken);
    ctx.state.Api.setRefreshToken(response.data.session.refreshToken);

    return response;
  } catch (err) {
    throw err;
  }
}

async function getVisitorToken(ctx, deviceSignature) {
  try {
    const opts = {
      path: '/visitor/activate'
    };
    const response = await ctx.state.Api.post(opts);
    ctx.state.Api.setSessionToken(response.data.accessToken);
    ctx.state.Api.setRefreshToken(response.data.refreshToken);

    return {
      visitorId: response.data.id,
      accessToken: response.data.accessToken,
      refreshToken: response.data.refreshToken
    };
  } catch (err) {
    throw err;
  }
}

async function getUserSubscription(ctx) {
  const opts = {
    path: '/user/subscriptions'
  };
  return ctx.state.Api.get(opts);
}

async function setSession(
  ctx,
  {
    session,
    userDetail,
    subscription,
    activation,
    geo,
    webviewSignin = false
  } = {}
) {
  const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
  const deviceSignature = activation.device_signature;
  Object.assign(session, { deviceSignature });

  const {
    channelPartnerId
  } = ctx.state.partnerConfiguration.CustomConfig.constants;
  unset(userDetail, 'spAccountID');
  const setupConfig = ctx.state.partnerConfiguration.CustomConfig.actions.setup;

  const payload = {
    channelPartnerId,
    session,
    device: ctx.state.device,
    user: userDetail,
    geo: {
      countryOfRegistration: userDetail.country,
      countryofLogin: geo.country,
      ip: geo.ip
    },
    subscription,
    isVisitor: ctx.state.isVisitor,
    isEvWebviewSignin: webviewSignin
  };

  if (setupConfig.customSessionPayload) {
    const customPayload = deepCompile(ctx, setupConfig.customSessionPayload);
    Object.assign(payload, customPayload);
  }

  await redis.setAsync(key, JSON.stringify(payload), 'EX', EXPIRE_7_DAYS);
  return;
}

/**
 * Build an HMAC for signin purposes based on HMAC collection for specific partner
 * @param {Object} Hmackeys
 * @param {Object} primaryId
 */
function buildHmac(Hmackeys, primaryId) {
  // get HMAC secret
  const keys = Object.keys(Hmackeys);
  const randKeys = keys[Math.floor(Math.random() * keys.length)];
  const secret = Hmackeys[randKeys];

  // get primary ID and use the first element from the array
  const id = Object.values(primaryId);
  if (id.length < 1) {
    throw new WebError(
      PRIMARY_ID_NOT_FOUND.message,
      PRIMARY_ID_NOT_FOUND.statusCode,
      PRIMARY_ID_NOT_FOUND.errorCode,
      PRIMARY_ID_NOT_FOUND.message,
      false,
      false
    );
  }

  const hmac = SHA256(id[0], secret);
  const hash = Base64.stringify(hmac);
  return `${randKeys}|${hash}`;
}

function setRedirectUrl(ctx) {
  const constantsConfig = ctx.state.partnerConfiguration.CustomConfig.constants;
  const redirectUrl =
    constantsConfig.redirection.enable &&
    isValidUrl(constantsConfig.redirection.url)
      ? `${constantsConfig.redirection.url}`
      : '';

  let path = `/`;
  const { redirectPage } = ctx.params;

  if (redirectPage) {
    const validPath = getValidPath(redirectPage);
    path = `/${validPath}`;
  }

  return `${redirectUrl}${path}`;
}

function getValidPath(path) {
  const validPath = ['movies', 'tvshows', 'channel', 'detail'];
  const splittedPath = path.split(':');
  const redirectPath = validPath.find(e => e === splittedPath[0]);

  let validUrl = '';
  if (redirectPath) {
    validUrl = redirectPath;

    if (splittedPath.length > 0 && redirectPath === 'detail') {
      validUrl = `${redirectPath}/${splittedPath[1]}?backToHome=true`;
    }

    if (splittedPath.length > 0 && redirectPath === 'channel') {
      validUrl =
        splittedPath[1] === undefined
          ? `${redirectPath}`
          : `${redirectPath}/${splittedPath[1]}`;
    }
  }

  return validUrl;
}

function logSetup(ctx, geo) {
  ctx.state.logger.info(`setup`, {
    query: { ...ctx.query },
    partner: { ...ctx.state[ctx.state.partnerId] },
    geo: { ...geo },
    device: { ...ctx.state.device }
  });
}

function getPrimaryId(ctx) {
  const setupConfig = ctx.state.partnerConfiguration.CustomConfig.actions.setup;
  return deepCompile(ctx, setupConfig.primaryId);
}

module.exports = {
  setup,
  verifyEvergent,
  getValidPath,
  getPrimaryId
};
