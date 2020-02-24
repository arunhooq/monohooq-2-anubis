const cloneDeep = require('lodash.clonedeep');
const get = require('lodash.get');
const { deepCompile } = require('asura-helpers/value');
const ApiError = require('../../Errors/ApiError');
const { generateLinkForCTA } = require('../../Helpers/CTAHandler');
const ApiHelper = require('../../../common/ApiHelper');
const { getClientGeo } = require('../../Helpers/IpAddress');
const xml = require('xml-js');
const {
  INVALID_SESSION,
  GENERIC_PLAY_API_ERROR
} = require('../../../common/ErrorCodes');

async function getPlay(ctx, next) {
  const { session, isVisitor, partnerConfiguration } = ctx.state;
  const paymentConfig = partnerConfiguration.CustomConfig.actions.payment;
  const constantsConfig = partnerConfiguration.CustomConfig.constants;
  const playbackConfig = get(
    partnerConfiguration,
    'CustomConfig.actions.playback',
    false
  );

  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    let pkg = getPkgByDevice(ctx);
    let headers = {
      'x-dhs-drm': pkg,
      'x-force-https': true,
      'x-force-eligibility': true,
      'x-force-invalidate': true,
      'x-strip-subtitle': true
    };

    if (partnerConfiguration) {
      const { customHeader } = deepCompile(ctx, playbackConfig);
      headers = Object.assign(headers, customHeader);
    }

    const response = await ctx.state.Api.get({
      path: `/play/${ctx.state.title.id}`,
      headers,
      enhanceHeaders: true,
      raw: true
    });

    if (response.data) {
      const enableVMAP = get(playbackConfig, 'vmap.enable', false);
      if (enableVMAP) {
        const contentUri = await processingVMAP(ctx, response.data);
        response.data.content = contentUri;
      }

      response.data.evId = ctx.state.session.user.spAccountId;
      response.data.metaInfo = buildMetaInfo(ctx, pkg);
      response.data.titleId = ctx.state.title.id;
      ctx.json(response, 200);
    }

    if (response.errors && response.errors.length) {
      const errResp = response.errors[0];
      switch (errResp.code) {
        case 'USR-2101': //You must subscribe to stream this content
          // if user came from EV webview signin
          if (ctx.state.session.isEvWebviewSignin) {
            throw new ApiError(errResp.detail, errResp.status, errResp.code);
          }

          const isUserEligible = await isEligibleForFreeTrial(ctx);
          if (isUserEligible) {
            let consent = cloneDeep(paymentConfig.consent);
            consent.cta = await generateLinkToCTACollection(
              ctx,
              consent.cta,
              constantsConfig.defaultSku
            );
            return ctx.json({
              data: {
                consent,
                showConsent: true
              }
            });
          }
          return ctx.json({
            data: {
              redirect: true,
              titleId: ctx.state.title.id
            }
          });
        case 'USR-2107': // NOTE: You must sign in to watch content
          if (isVisitor) {
            return ctx.json({
              data: { showSignIn: true }
            });
          }

          let consent = cloneDeep(paymentConfig.consent);
          consent.cta = await generateLinkToCTACollection(
            ctx,
            consent.cta,
            constantsConfig.defaultSku
          );
          return ctx.json({
            data: {
              consent,
              showConsent: true
            }
          });
        case 'PS-3000': // NOTE: session token is invalid
        case 'PS-2102': // NOTE: response from discover service is not correct
        case 'USR-2100': // NOTE: Content is not available in your region
        case 'USR-2102': // NOTE: You must rent the content to stream it
        case 'USR-2103': // NOTE: You have reach concurrent stream limit
        case 'USR-2104': // NOTE: Your tvod ticket is expired
        case 'USR-2106': // NOTE: R21 error
          throw new ApiError(errResp.detail, errResp.status, errResp.code);
        default:
          throw new ApiError(
            errResp.detail,
            GENERIC_PLAY_API_ERROR.statusCode,
            GENERIC_PLAY_API_ERROR.errorCode
          );
      }
    }
  } catch (err) {
    throw err;
  }
}

function getPkgByDevice(ctx) {
  if (isAppleDevice(ctx.state.device)) {
    return 'HLS/FAIRPLAY';
  } else {
    return 'DASH/WIDEVINE';
  }
}

function isAppleDevice(device) {
  const iDevices = ['iPhone', 'iPad', 'iPod'];
  const isIosModel = iDevices.find(e => e === device.modelNo);
  const isIos = device.os === 'iOS';
  const isApple = device.brand === 'Apple';
  return isIos || isIosModel || isApple;
}

function buildMetaInfo(ctx, pkg) {
  const episodeName =
    ctx.state.title.as === 'EPISODE' ? ctx.state.title.title : 'N/A';
  const contentType = ctx.state.title.as;
  const season = ctx.state.title.season ? ctx.state.title.season : 'N/A';
  const partnerName = ctx.state.partnerConfiguration.PartnerId;
  const show = ctx.state.title.title;
  const contentId =
    ctx.state.title.brightcove.id || ctx.state.title.quickplay.assetId;
  const evergentId = ctx.state.session.user.spAccountId;
  const titleId = ctx.state.title.id;
  const region = ctx.state.geo.country;

  return {
    titleId,
    evergentId,
    episodeName,
    contentType,
    contentId,
    partnerName,
    season,
    show,
    region,
    streamProtocol: pkg
  };
}

async function generateLinkToCTACollection(ctx, CTACollection = [], sku) {
  const mapperCTA = CTACollection.filter(cta => {
    if (cta.availableFor && Array.isArray(cta.availableFor)) {
      let deviceOS = ctx.state.device.os;
      if (deviceOS === 'webClient') {
        deviceOS = 'Android';
      } // make android as default
      return cta.availableFor.includes(deviceOS.toLowerCase()) === true;
    }
    return cta;
  }).map(async cta => {
    return await generateLinkForCTA(ctx, cta, sku);
  });
  const mappedCTA = await Promise.all(mapperCTA);
  return mappedCTA;
}

async function isEligibleForFreeTrial(ctx) {
  try {
    const opts = {
      path: '/proxy/isCustomerEligibleForFreeTrial'
    };
    const response = await ctx.state.Api.post(opts);
    return true;
  } catch (err) {
    return false;
  }
}

async function processingVMAP(ctx, playbackRequest = {}) {
  try {
    if (playbackRequest.hasOwnProperty('vmap') === true) {
      const geo = getClientGeo(ctx);
      const response = await ApiHelper.get({
        path: playbackRequest.vmap,
        headers: {
          'x-forwarded-for': geo.ip,
          'user-agent': ctx.state.request.headers['user-agent']
        }
      });
      const res = await response.text();
      const json = xml.xml2js(res, { compact: true });
      const contentUri = get(
        json,
        'vmap:VMAP.vmap:Extensions.bc:Brightcove._attributes.contenturi',
        playbackRequest.content
      );
      return contentUri;
    }

    return playbackRequest.content;
  } catch (err) {
    return playbackRequest.content;
  }
}

module.exports = {
  getPlay
};
