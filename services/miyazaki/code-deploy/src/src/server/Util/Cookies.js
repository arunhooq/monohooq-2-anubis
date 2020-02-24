function getCookies(cookies) {
  let list = {};

  cookies &&
    cookies.split(';').forEach(function(cookie) {
      const parts = cookie.split('=');
      list[parts[0].trim()] = parts[1].trim();
    });

  return list;
}

function createCookiesOpts(ctx) {
  const { cookie } = ctx.state.partnerConfiguration.CustomConfig.constants;
  let cookieOpts = {};

  // set cookie options
  if (cookie.domain.enable) {
    cookieOpts.domain = cookie.domain.url;
    cookieOpts.maxAge = cookie.domain.expiry;
    cookieOpts.overwrite = cookie.domain.overwrite;
  }

  return cookieOpts;
}

module.exports = {
  getCookies,
  createCookiesOpts
};
