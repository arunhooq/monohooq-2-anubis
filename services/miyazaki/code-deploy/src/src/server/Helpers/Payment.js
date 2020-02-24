const JwtParser = require('../Util/JwtParser');

function preparePaymentUrlParams(
  { ctx, sku, callbackUrl, cancelCallbackUrl, eligibleForTrial = false } = {},
  customPayload = {}
) {
  const { session } = ctx.state;
  const { constants } = ctx.state.partnerConfiguration.CustomConfig;

  const jwtPayload = JwtParser(session.session.accessToken);
  const sessionToken = jwtPayload.jti;
  const locale = constants.locale;
  const queryParams = {
    sessionToken,
    locale,
    sku,
    callback_url: callbackUrl,
    cancel_callback_url: cancelCallbackUrl,
    eligible_for_trial: eligibleForTrial
  };
  return { ...queryParams, ...customPayload };
}

module.exports = {
  preparePaymentUrlParams
};
