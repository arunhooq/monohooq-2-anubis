import GenericError from './GenericError';
import responseSerializer from '../log/responseSerializer';

/**
 * Error class to represent issues encountered when a route / page is not found
 * Can also be used to indicate that a feature is not available in a certain region
 */
export default class NotFoundError extends GenericError {
  /**
   *
   * @param {string} message Error message
   * @param {number} statusCode Http status code
   */
  constructor(message, response) {
    super(message, 404);
    this.name = 'NotFoundError';
    this.response = response;
  }

  getLogObject() {
    return {
      // ...super.getLogObject(),
      name: this.name,
      response: responseSerializer(this.response)
    };
  }
}
