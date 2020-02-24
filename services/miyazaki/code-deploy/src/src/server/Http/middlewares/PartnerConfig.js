const { cloneDeep, unset } = require('lodash');
const PartnerConfig = require('../../Services/PartnerConfig');

function getPartnerId(host) {
  const splittedHost = host.split('.');
  const partnerId = splittedHost[0].replace(
    /(?:-nightly)|(?:-preprod)|(?:-loadtesting)|(?:-prod)|(?:-sandbox)+/gi,
    ''
  );
  return partnerId;
}

function createPartnerWebConfiguration(partnerConfig) {
  const webPartnerConfiguration = cloneDeep(partnerConfig);

  const omittedKeys = [
    'HMACKeys',
    'CustomConfig.constants.application',
    'CustomConfig.constants.credential',
    'CustomConfig.middlewares'
  ];

  // Remove omitted keys from configuration
  for (let key of omittedKeys) {
    unset(webPartnerConfiguration, key);
  }

  return webPartnerConfiguration;
}

module.exports = async function(ctx, next) {
  try {
    const partnerId = getPartnerId(ctx.state.request.host);
    const partnerConfig = await PartnerConfig.getPartnerConfig(ctx, partnerId);
    ctx.state.partnerId = partnerId;
    ctx.state.partnerConfiguration = partnerConfig.partner;
    ctx.state.webPartnerConfiguration = createPartnerWebConfiguration(
      partnerConfig.partner
    );

    return next();
  } catch (err) {
    throw err;
  }
};
