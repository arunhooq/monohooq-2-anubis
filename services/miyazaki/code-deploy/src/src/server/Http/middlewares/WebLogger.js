module.exports = async (ctx, next) => {
  await next();

  ctx.state.logger.info(`${ctx.state.logger.key}-web`, {
    partnerId: ctx.state.partnerId,
    sessionId: ctx.state.sessionId,
    requestId: ctx.state.requestId,
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
    session: {
      ...ctx.state.session
    },
    region: ctx.state.geo
  });
};
