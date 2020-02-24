const JsonApiHelper = require('../../../common/JsonApiHelper');

module.exports = async (ctx, next) => {
  let { titleId } = ctx.params;
  let discoverUrl = ctx.state.env.DISCOVER_BASE_URL;
  if (!['production', 'preproduction', 'sandbox'].includes(ctx.state.env.ENV)) {
    discoverUrl = ctx.state.env.STAGING_DISCOVER_BASE_URL;
    titleId = ctx.state.env.STAGING_TITLE_ID;
  }

  const title = await JsonApiHelper.get({
    path: `${discoverUrl}/discover/titles/${titleId}`
  });
  ctx.state.title = title.data;

  return next();
};
