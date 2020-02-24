const parser = require('ua-parser-js');
const uuid = require('uuid/v4');
const { deepCompile } = require('asura-helpers/value');

module.exports = async (ctx, next) => {
  let deviceId = uuid().toString();
  const partner = ctx.state[ctx.state.partnerId];

  if (partner) {
    const deviceConfig =
      ctx.state.partnerConfiguration.CustomConfig.middlewares.device;
    deviceId = deepCompile(ctx, deviceConfig.deviceId);
  }

  const ua = parser(ctx.state.request.headers['user-agent']);
  let device = {
    serialNo: deviceId,
    name: 'HOOQ Webview - v1.0.0',
    type: 'webClient',
    modelNo: ua.device.model || 'webClient',
    brand: ua.device.vendor || 'webClient',
    os: ua.os.name || 'webClient',
    osVersion: ua.os.version || '1.0.0'
  };

  if (ctx.state.session) {
    const sessionDevice = ctx.state.session.device;

    if (device.os === sessionDevice.os) {
      ctx.state.device = ctx.state.session.device;
      return next();
    }

    // TODO: Set device to session cache
  }

  ctx.state.device = device;
  return next();
};
