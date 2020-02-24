const get = require('lodash.get');

function getClientGeo(ctx) {
  const geoConfig = ctx.state.partnerConfiguration.CustomConfig.middlewares.geo;
  const geoOutputPath = geoConfig.output;
  const geo = get(ctx.state, geoOutputPath.substring(1));

  return geo;
}

module.exports = {
  getClientGeo
};
