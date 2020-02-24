const JwtDecode = require('jwt-decode');

module.exports = function(token) {
  return JwtDecode(token);
};
