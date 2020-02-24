const maxmind = require('maxmind-mmdb');

let lookup;
module.exports = async ip => {
  if (!lookup) {
    lookup = await maxmind(process.env.MMDB_VERSION || 'lite');
  }

  if (!ip) {
    return { country: process.env.DEFAULT_TEST_REGION, default: true };
  }

  let result = lookup.get(ip);
  if (result && result.country) {
    return {
      country: result.country.iso_code
    };
  }

  return { country: process.env.DEFAULT_TEST_REGION, default: true };
};
