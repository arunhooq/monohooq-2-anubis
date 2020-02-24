const { enc, HmacSHA256 } = require('crypto-js');

exports.create = function(message, key) {
  const encrypted = HmacSHA256(message, key);
  return enc.Hex.stringify(encrypted);
};
