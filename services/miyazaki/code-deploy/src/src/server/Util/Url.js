const isUrl = require('isurl');

function isValidUrl(url) {
  return isUrl(new URL(url));
}

function generatePaymentUrl(path, queryParams) {
  const queryString = Object.keys(queryParams)
    .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(queryParams[k])}`)
    .join('&');
  const fullUrl = `${path}?${queryString}`;
  return fullUrl;
}

module.exports = {
  isValidUrl,
  generatePaymentUrl
};
