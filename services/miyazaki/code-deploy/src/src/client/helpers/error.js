const CLIENT_ERROR_NAME = 'ClientError';
module.exports = CLIENT_ERROR_NAME;

/**
 * Error class to represent issues encountered with either
 * Sanctuary API or NOVA API
 */
module.exports = class ClientError extends Error {
  /**
   *
   * @param {string} message Error message
   * @param {string} errorCode Error code that we got from Miyazaki server side or custom error code from client side
   */
  constructor(message, errorCode) {
    super(message);
    this.name = CLIENT_ERROR_NAME;
    this.errorCode = errorCode;
  }

  render() {
    const error = {
      name: this.name,
      code: this.errorCode,
      detail: this.message
    };
    return error;
  }
};
