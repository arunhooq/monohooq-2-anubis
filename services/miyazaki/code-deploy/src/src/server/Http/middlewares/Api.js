const HOOQApi = require('../../Services/HooqApi');
const redis = require('../../../connections/redis');
const WebError = require('../../Errors/WebError');
const { INVALID_REQUEST } = require('../../../common/ErrorCodes');
const { getClientGeo } = require('../../Helpers/IpAddress');

module.exports = async (ctx, next) => {
  const { device, session, partnerConfiguration, requestId } = ctx.state;
  let sessionToken = null;
  let refreshToken = null;
  let deviceSignature = null;
  let isVisitor = true;

  if (session) {
    isVisitor = session.isVisitor;
    sessionToken = session.session.accessToken;
    refreshToken = session.session.refreshToken;
    deviceSignature = session.session.deviceSignature;
  }

  const clientGeo = getClientGeo(ctx);
  const xRealIp =
    ctx.state.env.ENV === 'development'
      ? partnerConfiguration.CustomConfig.middlewares.geo.input.defaultTestIp
      : clientGeo.ip;

  const Api = new HOOQApi({
    clientHeaders: {
      'user-agent': ctx.request.headers['user-agent'],
      'x-forwarded-for': xRealIp,
      'x-real-ip': xRealIp
    },
    apikey: partnerConfiguration.CustomConfig.constants.application.secret,
    deviceId: device.serialNo,
    deviceModel: device.modelNo,
    deviceOS: device.os,
    deviceOSVersion: device.osVersion,
    deviceType: device.type,
    sessionToken,
    refreshToken,
    deviceSignature,
    xRealIp,
    requestId
  });

  if (
    (Api.sessionToken && Api.isTokenExpired()) ||
    ctx.state.shouldRefreshToken
  ) {
    try {
      let tokens;
      if (isVisitor) {
        tokens = await Api.updateVisitorToken();
      } else {
        tokens = await Api.updateToken(Api.refreshToken);
      }
      Api.accessToken = tokens.accessToken;
      Api.refreshToken = tokens.refreshToken;

      const sessionCache = ctx.state.session;
      sessionCache.session.accessToken = tokens.accessToken;
      sessionCache.session.refreshToken = tokens.refreshToken;

      const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
      await redis.setAsync(key, JSON.stringify(sessionCache));
    } catch (err) {
      throw new WebError(
        err.message,
        INVALID_REQUEST.statusCode,
        INVALID_REQUEST.errorCode,
        INVALID_REQUEST.message,
        false,
        false,
        true
      );
    }

    if (ctx.state.shouldRefreshToken) {
      ctx.state.shouldRefreshToken = false;
    }
  }

  ctx.state.Api = Api;
  return next();
};
