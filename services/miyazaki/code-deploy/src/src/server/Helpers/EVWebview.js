const { generatePaymentUrl } = require('../Util/Url');
const { checkOSAvailability } = require('../Helpers/Device');

function createEVWebviewUrl(
  { serialNo, deviceName, deviceType, modelNo } = {},
  EvBaseUrl,
  returnUrl,
  withSignup = false
) {
  const url = `${EvBaseUrl}/signinmobile`;
  let params = { returnUrl, serialNo, deviceName, deviceType, modelNo };
  if (!withSignup) {
    Object.assign(params, { signUp: 'OFF' });
  }

  return generatePaymentUrl(url, params);
}

function isOverrideEVSignin(evConfig, device) {
  return (
    evConfig &&
    evConfig.enable &&
    checkOSAvailability(evConfig.availableFor, device)
  );
}

module.exports = {
  createEVWebviewUrl,
  isOverrideEVSignin
};
