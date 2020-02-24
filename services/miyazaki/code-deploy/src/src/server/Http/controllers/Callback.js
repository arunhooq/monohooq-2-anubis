const redis = require('../../../connections/redis');
const { getPayload } = require('../../Helpers/Redis');
const JsonApiHelper = require('../../../common/JsonApiHelper');
const ApiError = require('../../Errors/ApiError');
const WebError = require('../../Errors/WebError');
const {
  INVALID_SESSION,
  TITLE_NOT_FOUND,
  PAGE_NOT_FOUND
} = require('../../../common/ErrorCodes');
const { getUserSubscription } = require('./Subscription');

const EXPIRE_5_MINUTE = 300;
const EXPIRE_7_DAYS = 604800;

async function callback(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    const { redirectId, status } = ctx.params;
    const key = `${ctx.state.redisKey}:redirect:${redirectId}`;
    const redirectProperties = await getPayload(key);

    const { titleId, destinationPath } = redirectProperties;
    if (!titleId) {
      ctx.redirectTo('/');
      return;
    }
    const parentId = await getParentId(ctx, titleId);

    await redis.setAsync(
      key,
      JSON.stringify({ ...redirectProperties, paymentStatus: status }),
      'EX',
      EXPIRE_5_MINUTE
    );
    const redirectUrl = await getRedirectionURL(ctx, {
      destinationPath,
      parentId,
      redirectId
    });
    const { spAccountId, sku, action } = redirectProperties;

    if (status === 'payment_success') {
      await updateUserSubscription(ctx);
    }

    ctx.state.logger.info(`${ctx.state.logger.key}-${action}`, {
      partnerId: ctx.state.partnerId,
      action,
      spAccountId,
      sku,
      status,
      redirectUrl
    });
    ctx.redirectTo(redirectUrl);
  } catch (err) {
    throw new WebError(
      err,
      PAGE_NOT_FOUND.statusCode,
      PAGE_NOT_FOUND.errorCode,
      PAGE_NOT_FOUND.message,
      false,
      false
    );
  }
}

async function getParentId(ctx, titleId) {
  try {
    const title = await JsonApiHelper.get({
      path: `${ctx.state.env.DISCOVER_BASE_URL}/discover/titles/${titleId}`
    });

    if (title.data.as === 'EPISODE') {
      return title.data.parent_id;
    }

    return title.data.id;
  } catch (err) {
    throw new WebError(
      err,
      TITLE_NOT_FOUND.statusCode,
      TITLE_NOT_FOUND.errorCode,
      TITLE_NOT_FOUND.message,
      false,
      false
    );
  }
}

async function getRedirectionURL(
  ctx,
  { destinationPath, parentId, redirectId }
) {
  try {
    const constantsConfig =
      ctx.state.partnerConfiguration.CustomConfig.constants;
    switch (destinationPath) {
      case 'detail':
        return `${
          constantsConfig.redirection.url
        }/detail/${parentId}?redirectId=${redirectId}&backToHome=true`;
      default:
        return `${constantsConfig.redirection.url}`;
    }
  } catch (err) {
    throw err;
  }
}

async function updateUserSubscription(ctx) {
  const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
  const session = await getPayload(key);
  const userSubscription = await getUserSubscription(ctx);
  const subscription =
    userSubscription.data.length !== 0 ? userSubscription.data : [];
  await redis.setAsync(
    key,
    JSON.stringify({ ...session, subscription }),
    'EX',
    EXPIRE_7_DAYS
  );
}

module.exports = {
  callback
};
