const { CTAType } = require('../../common/General');
const Url = require('../Util/Url');
const { preparePaymentUrlParams } = require('./Payment');
const { storeRedirectionProperties } = require('./Redis');
const { isCustomerEligibleForFreeTrial } = require('../Services/Subscription');
const { isEligibleForFreeTrial } = require('../Helpers/Subscription');

async function generatePaymentUrl(ctx, sku) {
  const titleId = ctx.state.title.id;
  const paymentConfig =
    ctx.state.partnerConfiguration.CustomConfig.actions.payment;
  const constantsConfig = ctx.state.partnerConfiguration.CustomConfig.constants;

  const redirectId = await storeRedirectionProperties(ctx, {
    action: 'payment',
    spAccountId: ctx.state.session.user.spAccountId,
    titleId,
    sku: sku.serviceID,
    ccSku: sku.ccServiceID,
    destinationPath: 'detail'
  });
  const eligibility = await isCustomerEligibleForFreeTrial(ctx);
  const eligibleForTrial = isEligibleForFreeTrial(ctx, eligibility);

  const queryParams = preparePaymentUrlParams(
    {
      ctx,
      sku: constantsConfig.defaultSku.serviceID,
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
  const paymentUrl = Url.generatePaymentUrl(paymentConfig.baseUrl, queryParams);
  return paymentUrl;
}

async function generateLinkForCTA(ctx, cta, sku) {
  const { CustomConfig } = ctx.state.partnerConfiguration;
  const { appUrl } = CustomConfig.constants;

  switch (cta.type) {
    case CTAType.CREATE_PAYMENT_URL:
      cta.opts = {
        redirection: true
      };
      cta.link = await generatePaymentUrl(ctx, sku);
      return cta;
    case CTAType.CREATE_PLAN_URL:
      cta.opts = {
        redirection: true
      };
      cta.link = `${appUrl}/plans?titleId=${ctx.state.title.id}`;
      return cta;
    case CTAType.DIRECT_ACTIVATION:
      cta.opts = {
        redirection: false,
        method: 'post',
        payload: {
          sku: cta.sku
        },
        path: '/activate'
      };
      return cta;
    case CTAType.CANCEL:
      cta.opts = {
        cancel: true
      };
      return cta;
    default:
      return cta;
  }
}

module.exports = {
  generateLinkForCTA
};
