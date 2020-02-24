const PartnerConfig = require('../../Services/PartnerConfig');

function getPartnerId(host) {
  const splittedHost = host.split('.');
  const partnerId = splittedHost[0].replace(
    /(?:-nightly)|(?:-preprod)|(?:-loadtesting)|(?:-prod)|(?:-sandbox)+/gi,
    ''
  );
  return partnerId;
}

module.exports = async function(ctx, next) {
  if (ctx.state.env.ENV === 'production') {
    return next();
  }

  try {
    const partnerId = getPartnerId(ctx.state.request.host);
    const partnerConfig = await PartnerConfig.getPartnerConfigHealthCheck(
      ctx,
      partnerId
    );
    return ctx.json(
      {
        data: partnerConfig.data
      },
      200
    );
  } catch (err) {
    throw err;
  }
};
