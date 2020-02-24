const WebError = require('../../Errors/WebError');
const ApiError = require('../../Errors/ApiError');
const { UNAUTHENTICATED_COOKIE } = require('../../../common/ErrorCodes');

module.exports = (isWeb = true) => {
  return async (ctx, next) => {
    const {
      cookieKey
    } = ctx.state.partnerConfiguration.CustomConfig.middlewares.session;
    const cookieSession = ctx.state.cookies[cookieKey];
    if (!cookieSession) {
      if (isWeb) {
        throw new WebError(
          UNAUTHENTICATED_COOKIE.message,
          UNAUTHENTICATED_COOKIE.statusCode,
          UNAUTHENTICATED_COOKIE.errorCode,
          UNAUTHENTICATED_COOKIE.message,
          false,
          false,
          true
        );
      }
      throw new ApiError(
        UNAUTHENTICATED_COOKIE.message,
        UNAUTHENTICATED_COOKIE.statusCode,
        UNAUTHENTICATED_COOKIE.errorCode
      );
    }
    return next();
  };
};
