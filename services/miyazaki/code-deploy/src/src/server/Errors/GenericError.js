const GENERIC_ERROR_CODE = 'GENERIC-0001';
module.exports = class GenericError extends Error {
  /**
   * @param {string} errorMessage Error message
   * @param {number} statusCode Http status code
   */
  constructor(errorMessage, statusCode, errorCode = GENERIC_ERROR_CODE) {
    super(errorMessage, statusCode);
    this.name = 'GenericError';
    this.statusCode = statusCode;
    this.errorCode = errorCode;
  }

  render() {
    const error = {
      name: this.name,
      statusCode: this.statusCode,
      errorCode: this.errorCode,
      detail: this.message
    };

    if (process.env.ENV !== 'production') {
      error.trace = this.stack;
    }

    return {
      errors: [error]
    };
  }

  getLogObject() {
    return {
      name: this.name,
      message: this.message,
      statusCode: this.statusCode,
      trace: this.stack
    };
  }
};
