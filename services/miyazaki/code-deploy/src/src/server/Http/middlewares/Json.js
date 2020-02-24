const get = require('lodash.get');

module.exports = (ctx, next) => {
  ctx.json = (body, code) => {
    ctx.set('Cache-Control', 'no-store, no-cache, must-revalidate');
    ctx.set('Pragma', 'no-cache');
    ctx.set('no-cache', 'Set-Cookie');
    ctx.set('Expires', 0);

    if (body) {
      const meta = {
        requestId: ctx.state.requestId,
        now: +new Date()
      };

      Object.assign(body, { meta });
    }

    ctx.body = body;
    ctx.type = 'application/json;charset=utf-8';
    ctx.status = typeof code === 'number' ? code : 200;
    const partnerId = get(ctx, 'state.partnerConfig.partner.ChannelPartnerID');

    ctx.state.logger.info(`${ctx.state.logger.key}-response`, {
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
        status: ctx.status,
        body: ctx.body
      },
      device: {
        ...ctx.state.device
      }
    });
  };

  return next();
};
