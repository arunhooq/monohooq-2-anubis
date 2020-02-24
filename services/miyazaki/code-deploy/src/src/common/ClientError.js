const ErrorCodes = require('./ErrorCodes');

export default class ClientError extends Error {
  constructor(code = ErrorCodes.SOMETHING_BAD_HAPPENS.errorCode, ...params) {
    // Pass remaining arguments (including vendor specific ones) to parent constructor
    super(...params);

    // Maintains proper stack trace for where our error was thrown (only available on V8)
    if (Error.captureStackTrace) {
      Error.captureStackTrace(this, ClientError);
    }

    this.name = 'ClientError';
    this.code = code;
  }
}
