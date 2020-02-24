const ApiError = require('../../Errors/ApiError');
const Url = require('../../Util/Url');
const { preparePaymentUrlParams } = require('../../Helpers/Payment');
const {
  getPayload,
  storeRedirectionProperties
} = require('../../Helpers/Redis');
const {
  isCustomerEligibleForFreeTrial
} = require('../../Services/Subscription');
const { isEligibleForFreeTrial } = require('../../Helpers/Subscription');
const {
  INVALID_SESSION,
  MISSING_PARAMETERS
} = require('../../../common/ErrorCodes');

async function getPaymentStatus(ctx, next) {
  const { session, partnerConfiguration } = ctx.state;
  const paymentConfig = partnerConfiguration.CustomConfig.actions.payment;
  const constantsConfig = partnerConfiguration.CustomConfig.constants;

  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    const { redirectId } = ctx.params;
    if (!redirectId) {
      throw new ApiError(
        MISSING_PARAMETERS.message,
        MISSING_PARAMETERS.statusCode,
        MISSING_PARAMETERS.errorCode
      );
    }

    const properties = await getPayload(
      `${ctx.state.redisKey}:redirect:${redirectId}`
    );

    if (!isPaymentPropertiesValid(properties)) {
      return ctx.json(
        {
          data: null
        },
        200
      );
    }

    const ccRedirectId = await storeRedirectionProperties(ctx, {
      titleId: properties.titleId,
      sku: properties.ccSku,
      ccSku: properties.ccSku,
      destinationPath: 'detail'
    });

    const eligibility = await isCustomerEligibleForFreeTrial(ctx);
    const eligibleForTrial = isEligibleForFreeTrial(ctx, eligibility);

    const queryParams = preparePaymentUrlParams(
      {
        ctx,
        sku: properties.ccSku,
        callbackUrl: `${
          constantsConfig.redirection.url
        }/callback/${ccRedirectId}/payment_success`,
        cancelCallbackUrl: `${
          constantsConfig.redirection.url
        }/callback/${ccRedirectId}/payment_cancel`,
        eligibleForTrial
      },
      {
        externalToken: ctx.state.session.partnerToken
      }
    );
    const ccPaymentUrl = Url.generatePaymentUrl(
      paymentConfig.baseUrl,
      queryParams
    );
    logPayment(ctx, properties);
    return ctx.json(
      {
        data: {
          ccPaymentUrl,
          paymentStatus: properties.paymentStatus,
          titleId: properties.titleId
        }
      },
      200
    );
  } catch (err) {
    throw err;
  }
}

function logPayment(ctx, properties) {
  const eventName = `${ctx.state.logger.key}-payment`;
  ctx.state.logger.info(eventName, {
    cpCustomerId: ctx.state.session.user.cpCustomerId,
    spAccountId: ctx.state.session.user.spAccountId,
    partnerId: ctx.state.session.user.partnerId,
    country: ctx.state.geo.country,
    device: {
      ...ctx.state.device
    },
    paymentStatus: properties.paymentStatus,
    titleId: properties.titleId,
    sku: properties.sku,
    ccSku: properties.ccSku
  });
}

function isPaymentPropertiesValid(properties) {
  if (properties === undefined || properties === null) {
    return false;
  }

  return (
    properties.hasOwnProperty('titleId') &&
    properties.hasOwnProperty('sku') &&
    properties.hasOwnProperty('ccSku') &&
    properties.hasOwnProperty('paymentStatus')
  );
}

module.exports = {
  getPaymentStatus
};
