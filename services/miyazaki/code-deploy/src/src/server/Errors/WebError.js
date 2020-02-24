const GenericError = require('./GenericError');

const ERROR_NAME = 'WebError';
module.exports = ERROR_NAME;

/**
 * Error class to represent issues encountered with Web
 */
module.exports = class WebError extends GenericError {
  /**
   *
   * @param {string} message Error message
   * @param {number} statusCode Http status code
   * @param {number} errorCode Sanctuary or NOVA API error code
   */
  constructor(
    message,
    statusCode,
    errorCode,
    messageText = '',
    showTryAgainButton = true,
    showHeader = true,
    skipPartnerConfig = false
  ) {
    super(message, statusCode);
    this.name = ERROR_NAME;
    this.message = message;
    this.errorCode = errorCode;
    this.messageText = messageText;
    this.showTryAgainButton = showTryAgainButton;
    this.showHeader = showHeader;
    this.skipPartnerConfig = skipPartnerConfig;
  }
};
