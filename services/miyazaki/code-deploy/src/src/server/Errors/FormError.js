const ApiError = require('./ApiError');

const ERROR_MESSAGE = 'Form validation failed';

/**
 * Error class to represent issues like form validation errors,
 * server-side errors, etc
 */
module.exports = class FormError extends ApiError {
  static get ERROR_NAME() {
    return 'FormError';
  }

  /**
   *
   * @param {number} statusCode Http status code
   * @param {number} errorCode Sanctuary or Promo API error code
   */
  constructor(statusCode, response, errorCode) {
    super(ERROR_MESSAGE, statusCode, response, errorCode);
    this.name = FormError.ERROR_NAME;
    this.fields = {};
  }

  addField(name, message) {
    this.fields[name] = message;
  }

  numInvalidFields() {
    return Object.keys(this.fields).length;
  }

  render() {
    const error = {
      name: this.name,
      code: this.errorCode,
      statusCode: this.statusCode,
      detail: this.message,
      fields: this.fields
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
      // ...super.getLogObject(),
      name: this.name,
      fields: this.fields
    };
  }
};
