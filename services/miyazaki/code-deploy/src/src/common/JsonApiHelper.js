const ApiHelper = require('./ApiHelper');
const FormError = require('../server/Errors/FormError');
const ApiError = require('../server/Errors/ApiError');

function checkForEmptyResponseBody(response) {
  const httpStatus = response.status;
  const hasEmptyResponse = httpStatus === 204 || httpStatus === 205;
  const body = hasEmptyResponse ? {} : response.data;

  return {
    httpStatus,
    body
  };
}

class JsonApiHelper {
  static checkFormErrors(requestArgs, response) {
    const { httpStatus, body } = response;
    const isOk = httpStatus >= 200 && httpStatus < 300;
    if (isOk) {
      return body;
    }
    const isInvalidRequestError = httpStatus === 400;

    const errors = body.errors || [];
    const nonFormError = errors.find(e => e.name !== FormError.ERROR_NAME);

    if (isInvalidRequestError && !nonFormError) {
      // only form errors were reported. These are handled in the Forms directly
      return body;
    }

    // need to extract content of unexpected error
    const { detail, code } = nonFormError || {};
    const apiError = new ApiError(detail, 400, code, response, requestArgs);
    throw apiError;
  }

  static get(args) {
    return ApiHelper.get(args).then(response => response.data);
  }

  static post(args) {
    return ApiHelper.post(args)
      .then(checkForEmptyResponseBody)
      .then(JsonApiHelper.checkFormErrors.bind(null, args));
  }

  static put(args) {
    return ApiHelper.put(args)
      .then(checkForEmptyResponseBody)
      .then(JsonApiHelper.checkFormErrors.bind(null, args));
  }
}

module.exports = JsonApiHelper;
