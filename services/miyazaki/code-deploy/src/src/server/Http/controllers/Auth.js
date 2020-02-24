const { createEVWebviewUrl } = require('../../Helpers/EVWebview');
const redis = require('../../../connections/redis');

async function EvSignin(ctx, next) {
  const { device, partnerConfiguration } = ctx.state;
  const { appUrl } = partnerConfiguration.CustomConfig.constants;
  const returnUrl = `${appUrl}/ev/auth`;
  // TODO: take from partner config instead for variable below
  const withSignup = false;
  const baseUrl = ctx.state.env.EV_WEBVIEW_BASE_URL;

  const EvWebviewUrl = createEVWebviewUrl(
    {
      deviceName: device.name,
      serialNo: device.serialNo,
      deviceType: device.type,
      modelNo: device.modelNo
    },
    baseUrl,
    returnUrl,
    withSignup
  );

  return ctx.redirectTo(EvWebviewUrl);
}

async function logout(ctx, next) {
  try {
    const { sessionId, redisKey, partnerConfiguration, isVisitor } = ctx.state;
    const { constants } = partnerConfiguration.CustomConfig;

    if (isVisitor) {
      return ctx.redirectTo(`${constants.appUrl}`);
    }

    const opts = {
      path: '/user/signout'
    };
    await ctx.state.Api.post(opts);
    await redis.delAsync(`${redisKey}:session:${sessionId}`);

    return ctx.redirectTo(`${constants.appUrl}/setup?skipCheckSession=true`);
  } catch (err) {
    throw err;
  }
}

module.exports = {
  EvSignin,
  logout
};
