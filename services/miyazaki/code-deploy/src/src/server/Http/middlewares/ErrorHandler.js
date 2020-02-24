const get = require('lodash.get');

const NODE_DEBUG = process.env.NODE_DEBUG || 'false';
const DEFAULT_CODE_WEB = 'SHANTI-5001'; // error code for API should be more than 5001
const DEFAULT_CODE_API = 'SHANTI-5000'; // error code for API should be less than 5000

function createQueryParameter(queryParams = {}) {
  return Object.keys(queryParams)
    .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(queryParams[k])}`)
    .join('&');
}

/**
 * Middleware to catch an error
 * @param {Context} ctx
 * @param {function} next
 */
module.exports = async (ctx, next) => {
  try {
    await next();
  } catch (err) {
    const trace = NODE_DEBUG === 'true' ? err.stack : null;
    const partnerId = get(ctx, 'state.partnerConfig.partner.ChannelPartnerID');

    ctx.state.logger.error(`${ctx.state.logger.key}-error-response`, {
      partnerId: ctx.state.partnerId,
      sessionId: ctx.state.sessionId,
      requestId: ctx.state.requestId,
      partnerId,
      request: {
        method: ctx.state.request.method,
        header: ctx.state.request.header,
        body: ctx.state.request.body,
        hostname: ctx.state.request.hostname,
        path: ctx.state.request.path,
        origin: ctx.state.request.origin,
        query: ctx.query,
        cookies: ctx.state.cookies
      },
      response: {
        errorName: err.Name,
        errorMessage: err.message,
        errorCode: err.errorCode,
        status: err.statusCode
      },
      trace
    });

    if (err.name === 'WebError') {
      let queryParams = {};
      if (err.messageText !== '') {
        queryParams.message = err.messageText;
      }
      queryParams.showTryAgainButton = err.showTryAgainButton;
      queryParams.showHeader = err.showHeader;
      queryParams.skipPartnerConfig = err.skipPartnerConfig;

      let errorPath = `/error?${createQueryParameter(queryParams)}`;
      return ctx.redirect(errorPath);
    } else {
      ctx.type = 'application/json;charset=utf-8';
      ctx.status = err.statusCode || 500;
      ctx.body = {
        errors: [
          {
            status: err.statusCode || 500,
            name: err.name,
            detail: err.message,
            code: err.errorCode || DEFAULT_CODE_WEB,
            trace
          }
        ],
        meta: {
          requestId: ctx.state.requestId,
          now: +new Date()
        }
      };

      return;
    }
  }
};
