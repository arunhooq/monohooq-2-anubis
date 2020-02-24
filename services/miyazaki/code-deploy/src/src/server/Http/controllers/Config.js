const ApiError = require('../../Errors/ApiError');
const Url = require('../../Util/Url');
const { preparePaymentUrlParams } = require('../../Helpers/Payment');
const { storeRedirectionProperties } = require('../../Helpers/Redis');
const { getPlanPropertiesTranslation } = require('../../Helpers/Config');
const {
  isCustomerEligibleForFreeTrial
} = require('../../Services/Subscription');
const { isEligibleForFreeTrial } = require('../../Helpers/Subscription');
const JsonApiHelper = require('../../../common/JsonApiHelper');
const { getTranslation } = require('../../Services/Translation');
const { INVALID_SESSION } = require('../../../common/ErrorCodes');

async function getPlansConfig(ctx, next) {
  const { session, partnerConfiguration, geo } = ctx.state;
  const constantsConfig = partnerConfiguration.CustomConfig.constants;
  const paymentConfig = partnerConfiguration.CustomConfig.actions.payment;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    const { titleId } = ctx.request.body;

    const translation = await JsonApiHelper.get({
      path: constantsConfig.translation
    });
    const eligibility = await isCustomerEligibleForFreeTrial(ctx);
    const eligibleForTrial = isEligibleForFreeTrial(ctx, eligibility);

    const skuList = constantsConfig.planSelectorSKU
      .filter(sku => sku.isAvailable)
      .map(async sku => {
        const planProperties = getPlanPropertiesTranslation(sku, translation);
        const redirectId = await storeRedirectionProperties(ctx, {
          titleId,
          sku: sku.serviceID,
          ccSku: sku.ccServiceID,
          destinationPath: 'detail'
        });

        const queryParams = preparePaymentUrlParams(
          {
            ctx,
            sku: sku.serviceID,
            callbackUrl: `${
              constantsConfig.redirection.url
            }/callback/${redirectId}/payment_success`,
            cancelCallbackUrl: `${
              constantsConfig.redirection.url
            }/callback/${redirectId}/payment_cancel`,
            eligibleForTrial
          },
          {
            externalToken: ctx.state.session.partnerToken
          }
        );
        const url = Url.generatePaymentUrl(paymentConfig.baseUrl, queryParams);
        return { ...sku, ...planProperties, url };
      });

    const sku = await Promise.all(skuList);
    const data = {
      sku,
      plan_cta_label: translation['plan_cta_label'],
      plan_cancel_label: translation['plan_cancel_label']
    };

    return ctx.json({ data }, 200);
  } catch (err) {
    throw err;
  }
}

async function getDetailConfig(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    const translation = await getTranslation(ctx);
    const {
      failedPaymentModal
    } = ctx.state.partnerConfiguration.CustomConfig.actions.payment;
    failedPaymentModal.cta.text =
      translation[failedPaymentModal.cta.translation];

    return ctx.json({ data: { failedPaymentModal } }, 200);
  } catch (err) {
    throw err;
  }
}

module.exports = {
  getDetailConfig,
  getPlansConfig
};
