const Router = require('koa-router');
const CookieMiddleware = require('../middlewares/Cookie');
const GeoCheckMiddleware = require('../middlewares/Geocheck');
const SessionMiddleware = require('../middlewares/Session');
const WebLoggerMiddleware = require('../middlewares/WebLogger');
const PartnerConfigMiddleware = require('../middlewares/PartnerConfig');
const PartnerConfigHealthCheckMiddleware = require('../middlewares/PartnerConfigHealthCheck');

function routes(nexApp, handle) {
  const route = new Router();
  const webMiddlewares = [
    PartnerConfigMiddleware,
    GeoCheckMiddleware,
    CookieMiddleware(),
    SessionMiddleware,
    WebLoggerMiddleware
  ];

  route.get('/', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, '/', {
      ...ctx.query,
      ...ctx.params,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/discover/:collectionId', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    const { collectionId } = ctx.params;
    await nexApp.render(ctx.request, ctx.res, `/collection`, {
      ...ctx.query,
      ...ctx.params,
      collectionId,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/detail/:movieId', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    const { movieId, redirectId } = ctx.params;
    await nexApp.render(ctx.req, ctx.res, `/detail`, {
      ...ctx.query,
      movieId,
      redirectId,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/channel/:channelId*', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    const { channelId } = ctx.params;
    await nexApp.render(ctx.req, ctx.res, `/channel`, {
      ...ctx.query,
      channelId,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/movies', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/movies`, {
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/tvshows', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/tvshows`, {
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/rental', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/rental`, {
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/terms', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/terms`, {
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/consent', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/consent`, {
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/policy/:company', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    const { company } = ctx.params;
    await nexApp.render(ctx.req, ctx.res, `/policy`, {
      ...ctx.query,
      company,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/search', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/search`, {
      ...ctx.query,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/related', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/search`, {
      ...ctx.query,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/plans', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/plans`, {
      ...ctx.query,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/subscription', ...webMiddlewares, async ctx => {
    ctx.status = 200;
    await nexApp.render(ctx.req, ctx.res, `/subscription`, {
      ...ctx.query,
      region: ctx.state.geo.country,
      userSession: ctx.state.webSession,
      partnerConfig: ctx.state.webPartnerConfiguration
    });
    ctx.respond = false;
  });

  route.get('/health', ...[PartnerConfigHealthCheckMiddleware], async ctx => {
    ctx.status = 200;
    ctx.respond = false;
  });

  route.get('/error', async ctx => {
    ctx.status = 400;
    ctx.query = {
      ...ctx.query
    };

    ctx.set('Cache-Control', 'no-store, no-cache, must-revalidate');
    ctx.set('Pragma', 'no-cache');
    ctx.set('Expires', 0);
    ctx.set('no-cache', 'Set-Cookie');

    await nexApp.render(ctx.req, ctx.res, `/error`, ctx.query);
    ctx.respond = false;
  });

  route.get('*', async ctx => {
    ctx.status = 200;
    await handle(ctx.req, ctx.res);
    ctx.respond = false;
  });

  return route;
}

module.exports = routes;
