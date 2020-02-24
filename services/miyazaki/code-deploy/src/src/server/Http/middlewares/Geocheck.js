const geocheck = require('../../Services/Geocheck');
const { geolock } = require('../../Util/Constants');
const WebError = require('../../Errors/WebError');
const { UNSUPPORTED_COUNTRY } = require('../../../common/ErrorCodes');
const isPrivateIp = require('../../Helpers/IsPrivateIp');
const isAwsIp = require('is-aws-ip');
const { setToOutput } = require('../../Util/Infer');

const getUserRealestIp = function(forwardedFor) {
  if (forwardedFor && typeof forwardedFor === 'string') {
    let ips = forwardedFor.split(',').map(ip => {
      return ip.trim();
    });
    ips = ips.filter(ip => {
      return !isAwsIp(ip) && !isPrivateIp(ip);
    });
    // get the first one
    const ip = ips[0];
    return ip;
  }
};

module.exports = async function(ctx, next) {
  const geoConfig = ctx.state.partnerConfiguration.CustomConfig.middlewares.geo;
  const userIp =
    ctx.request.headers['x-forwarded-for'] || geoConfig.input.defaultTestIp;
  const ip = getUserRealestIp(userIp);

  try {
    const isMockAllowed = geolock.mockAllowedEnvironment.includes(
      ctx.state.env.ENV
    );
    const geo = await geocheck(ip);

    setToOutput(ctx, geoConfig.output, { ip, country: geo.country });

    // if is mock allowed for certain env
    if (isMockAllowed) {
      setToOutput(ctx, geoConfig.output, {
        ip: geoConfig.input.defaultTestIp,
        country: geoConfig.input.defaultTestCountry
      });
    }

    // if session exist and has different country, overwrite the current session's country
    if (ctx.state.session) {
      const country =
        ctx.state.session.geo.countryofLogin !== geo.country
          ? geo.country
          : ctx.state.session.geo.countryofLogin;

      setToOutput(ctx, geoConfig.output, { ip, country });
    }

    // if country is not supported
    const isCountrySupported = geoConfig.input.supportedCountry.includes(
      ctx.state.geo.country
    );
    if (!isCountrySupported) {
      throw new WebError(
        UNSUPPORTED_COUNTRY.message,
        UNSUPPORTED_COUNTRY.statusCode,
        UNSUPPORTED_COUNTRY.errorCode,
        UNSUPPORTED_COUNTRY.message,
        false,
        false,
        true
      );
    }

    return next();
  } catch (err) {
    throw err;
  }
};
