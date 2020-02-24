const { staticCache } = require('../../Util/Constants');

module.exports = async (ctx, next) => {
  const isPathMatch = /^(\/_next\/static\/|\/static)/.test(ctx.request.url);
  const allowed = staticCache.overrideCacheControlEnvironment.includes(
    ctx.state.env.ENV
  );
  if (isPathMatch && allowed) {
    ctx.set('Cache-Control', 'public, max-age=31536000, immutable');
  }
  await next();
};
