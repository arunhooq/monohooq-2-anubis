const uuid = require('uuid');
const logger = require('../../Helpers/Logger');
const cookiesUtil = require('../../Util/Cookies');

module.exports = async function initState(ctx, next) {
  // Initial ctx.state value
  ctx.state = {
    redisKey: 'hooq:webview',
    request: ctx.request,
    query: ctx.query,
    env: process.env,
    isStaging: process.env.STAGING === 'true',
    logger: {
      ...logger,
      key: 'hooq-webview'
    },
    cookies: cookiesUtil.getCookies(ctx.request.headers.cookie),
    requestId: uuid.v4().toString(),
    isVisitor: true
  };

  if (ctx.state.isStaging) {
    const { id_user, id_device } = ctx.query;
    ctx.state.dummyAccount = {
      userId: id_user || uuid.v4().toString(),
      deviceId: id_device || uuid.v4().toString()
    };
  }

  return next();
};
