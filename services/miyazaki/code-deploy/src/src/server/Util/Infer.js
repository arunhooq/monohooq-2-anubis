const { set } = require('lodash');
const WebError = require('../Errors/WebError');
const {
  INVALID_CONFIGURATION,
  GENERIC_ERROR
} = require('../../common/ErrorCodes');

function setToOutput(ctx, output, value) {
  if (output[0] !== '@') {
    throw new WebError(
      INVALID_CONFIGURATION.message,
      INVALID_CONFIGURATION.statusCode,
      INVALID_CONFIGURATION.errorCode,
      GENERIC_ERROR.message,
      false,
      false
    );
  }

  set(ctx, `state.${output.slice(1)}`, value);
  return true;
}

module.exports = {
  setToOutput
};
