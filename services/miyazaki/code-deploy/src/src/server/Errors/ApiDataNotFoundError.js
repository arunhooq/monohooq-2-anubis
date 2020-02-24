const NotFoundError = require('./GenericError');
const responseSerializer = require('../log/responseSerializer');

/**
 * Error class to represent issues encountered when Api call return data not found
 */
module.exports = class ApiDataNotFoundError extends NotFoundError {
  /**
   *
   * @param {string} message Error message
   * @param {number} statusCode Http status code
   */
  constructor(message, response) {
    super(message, 404);
    this.name = 'ApiDataNotFoundError';
    this.response = response;
  }

  getLogObject() {
    return {
      // ...super.getLogObject(),
      name: this.name,
      response: responseSerializer(this.response)
    };
  }
};
