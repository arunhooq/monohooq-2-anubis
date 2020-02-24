/**
 * HttpError extends global Error object by adding status and code props.
 * @param {string} message
 * @param {number|string} status
 * @param {string} code
 * @param {string} code
 */

const { STATUS_CODES } = require('http');

module.exports = class HttpError extends Error {
  constructor(message, status, code, isWeb) {
    super(message);
    this.name = this.constructor.name;
    this.status = STATUS_CODES[status] ? Number(status) : 500;
    this.code = code;
    this.isWeb = isWeb === 'true' ? Boolean(isWeb) : false;
  }
};
