const ApiError = require('../../Errors/ApiError');
const redis = require('../../../connections/redis');
const { INVALID_SESSION } = require('../../../common/ErrorCodes');
const EXPIRE_7_DAYS = 604800;

async function activateUser(ctx, next) {
  const { session, redisKey, sessionId } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }
  const key = `${redisKey}:session:${sessionId}`;

  try {
    const { sku } = ctx.state.request.body;
    const opts = {
      path: '/user/activate',
      payload: {
        sku,
        method: 'activate'
      }
    };

    const response = await ctx.state.Api.post(opts);
    const subscription = await getUserSubscription(ctx);
    session.subscription = subscription.data;
    await redis.setAsync(key, JSON.stringify(session), 'EX', EXPIRE_7_DAYS);

    return ctx.json(
      {
        data: {
          subscription: subscription.data,
          message: response.data.message
        }
      },
      200
    );
  } catch (err) {
    throw err;
  }
}

async function getUserSubscription(ctx) {
  const opts = {
    path: '/user/subscriptions'
  };

  return ctx.state.Api.get(opts);
}

module.exports = {
  activateUser
};
