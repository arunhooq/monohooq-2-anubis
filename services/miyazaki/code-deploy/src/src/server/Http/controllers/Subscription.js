const get = require('lodash.get');
const { getPayload } = require('../../Helpers/Redis');
const redis = require('../../../connections/redis');
const {
  checkStatus,
  addSubscriptionProperties
} = require('../../Helpers/Subscription');
const UserHelper = require('../../Helpers/User');
const ApiError = require('../../Errors/ApiError');
const WebError = require('../../Errors/WebError');
const {
  INVALID_SESSION,
  MISSING_PARAMETERS,
  INVALID_REQUEST
} = require('../../../common/ErrorCodes');
const {
  filterActiveSVODSubscriptions
} = require('../../../common/Subscription');
const {
  isCustomerEligibleForFreeTrial
} = require('../../Services/Subscription');
const EXPIRE_7_DAYS = 604800;

async function getStatus(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    const [subscriptions, eligibility] = await Promise.all([
      getUserSubscription(ctx),
      isCustomerEligibleForFreeTrial(ctx)
    ]);

    let activeSVODSubscriptions = [];
    if (subscriptions.data && subscriptions.data.length) {
      activeSVODSubscriptions = filterActiveSVODSubscriptions(
        subscriptions.data
      );
    }

    const svodSubscriptions = addSubscriptionProperties(
      ctx,
      activeSVODSubscriptions
    );
    const status = checkStatus(ctx, svodSubscriptions, eligibility);

    const paymentMethodResponse = await getPaymentMethod(ctx);
    const paymentMethod = get(
      paymentMethodResponse,
      'data.PaymentMethods[0].label',
      ''
    );
    const userDetail = await UserHelper.getUserDetail(ctx);

    const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
    const payload = await getPayload(key);
    await redis.setAsync(
      key,
      JSON.stringify({
        ...payload,
        status,
        paymentMethod,
        subscription: svodSubscriptions,
        user: userDetail
      }),
      'EX',
      EXPIRE_7_DAYS
    );

    return ctx.json(
      { data: { status, paymentMethod, subscription: svodSubscriptions } },
      200
    );
  } catch (err) {
    throw err;
  }
}

async function getUserSubscription(ctx) {
  try {
    const opts = {
      path: '/user/subscriptions'
    };
    return ctx.state.Api.get(opts);
  } catch (err) {
    throw new WebError(
      err.message,
      INVALID_REQUEST.statusCode,
      INVALID_REQUEST.errorCode,
      INVALID_REQUEST.message,
      false
    );
  }
}

async function cancelSubscription(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  const { sku, endDate } = ctx.state.request.body;
  if (!sku) {
    throw new ApiError(
      MISSING_PARAMETERS.message,
      MISSING_PARAMETERS.statusCode,
      MISSING_PARAMETERS.errorCode
    );
  }

  try {
    const payload = {
      sku,
      method: 'deactivate'
    };

    if (endDate) {
      payload.endDate = endDate;
    }

    const opts = {
      path: '/user/activate',
      payload
    };

    const response = await ctx.state.Api.post(opts);

    return ctx.json({ data: response.data }, 200);
  } catch (err) {
    throw err;
  }
}

async function getPaymentMethod(ctx) {
  const opts = {
    path: '/proxy/getPaymentMethods'
  };

  return ctx.state.Api.post(opts);
}

module.exports = {
  getStatus,
  cancelSubscription,
  getUserSubscription
};
