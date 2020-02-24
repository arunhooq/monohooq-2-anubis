const get = require('lodash.get');
module.exports = (ctx, next) => {
  ctx.redirectTo = redirectUrl => {
    const partnerId = get(ctx, 'state.partnerConfig.partner.ChannelPartnerID');

    ctx.state.logger.info(`${ctx.state.logger.key}-redirect`, {
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
        redirectUrl
      },
      device: {
        ...ctx.state.device
      }
    });

    ctx.redirect(redirectUrl);
  };

  return next();
};
