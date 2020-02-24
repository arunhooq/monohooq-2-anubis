import GenericError from './GenericError';
import responseSerializer from '../log/responseSerializer';

/**
 * Error class to represent issues encountered during authentication
 */
export default class AuthError extends GenericError {
  /**
   *
   * @param {string} message Error message
   * @param {number} statusCode Http status code
   * @param {number} errorCode Sanctuary or NOVA API error code
   */
  constructor(message, response, statusCode = 500) {
    super(message, statusCode);
    this.name = 'AuthError';
    this.statusCode = statusCode;
  }

  getLogObject() {
    return {
      // ...super.getLogObject(),
      name: this.name,
      statusCode: this.statusCode,
      response: responseSerializer(this.response)
    };
  }
}
