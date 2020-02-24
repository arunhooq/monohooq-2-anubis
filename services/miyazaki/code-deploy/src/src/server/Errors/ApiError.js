const GenericError = require('./GenericError');

const API_ERROR_NAME = 'ApiError';
module.exports = API_ERROR_NAME;

/**
 * Error class to represent issues encountered with either
 * Sanctuary API or NOVA API
 */
module.exports = class ApiError extends GenericError {
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
    response,
    requestArgs = undefined
  ) {
    super(message, statusCode);
    this.name = API_ERROR_NAME;
    this.errorCode = errorCode;
    this.response = response;
    this.requestArgs = requestArgs;
  }

  render() {
    const error = {
      name: this.name,
      statusCode: this.statusCode,
      originalStatusCode: this.response && this.response.status,
      code: this.errorCode,
      detail: this.message
    };

    if (process.env.ENV !== 'production') {
      error.trace = this.stack;
      error.response = this.response;
      error.requestArgs = this.requestArgs;
    }

    return {
      errors: [error]
    };
  }
};
