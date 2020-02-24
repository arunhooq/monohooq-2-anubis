const ApiError = require('../server/Errors/ApiError');
const {
  INVALID_API_KEY,
  INVALID_JSON_RESPONSE,
  ERROR_READING_SERVER_RESPONSE,
  KONG_INVALID_API_KEY,
  KONG_MISSING_API_KEY
} = require('./ErrorCodes');
const { HttpMethod } = require('./General');
const axios = require('axios');

class ApiHelper {
  static createQueryString(queryParams = {}) {
    return Object.keys(queryParams)
      .map(
        k => `${encodeURIComponent(k)}=${encodeURIComponent(queryParams[k])}`
      )
      .join('&');
  }

  static createHeaders(overrides = {}) {
    const headers = {
      'Content-Type': 'application/json;charset=utf-8'
    };
    Object.assign(headers, overrides);
    return headers;
  }

  static get({
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    const queryString = ApiHelper.createQueryString(payload);
    const getPath = queryString ? `${path}?${queryString}` : path;

    return ApiHelper.makeRequest({
      method: HttpMethod.GET,
      path: getPath,
      headers,
      raw,
      withCredentials
    });
  }

  static post({
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    return ApiHelper.makeRequest({
      method: HttpMethod.POST,
      path,
      payload,
      headers,
      raw,
      withCredentials
    });
  }

  static put({
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    return ApiHelper.makeRequest({
      method: HttpMethod.PUT,
      path,
      payload,
      headers,
      raw,
      withCredentials
    });
  }

  static patch({
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    return ApiHelper.makeRequest({
      method: HttpMethod.PATCH,
      path,
      payload,
      headers,
      raw,
      withCredentials
    });
  }

  static delete({
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    return ApiHelper.makeRequest({
      method: HttpMethod.DELETE,
      path,
      payload,
      headers,
      raw,
      withCredentials
    });
  }

  static async checkStatus(requestArgs, response) {
    const httpStatus = response.status;
    const isOk = httpStatus >= 200 && httpStatus < 300;
    const hasError = httpStatus >= 400;
    if (isOk || !hasError) {
      return response;
    }

    // body can only be consumed once, so we only do it here if we are sure that an error occurred
    let error;
    try {
      const content = response.data;
      const errors = content.errors || content.error || [];
      if (Array.isArray(errors)) {
        error = errors[0];
      } else {
        error = errors;
      }

      // Due to limitation in Kong's Api Key Plugin, error message format for api key will not be
      // consistent with all other errors passed from Kong Api. Error message related to api key
      // will be returned in the following format: { message: "Error Message" }
      if (
        content.message === KONG_INVALID_API_KEY ||
        content.message === KONG_MISSING_API_KEY
      ) {
        error = {
          detail: content.message,
          code: INVALID_API_KEY
        };
      }
    } catch (err) {
      if (err.name === 'SyntaxError') {
        // json was not parsable
        error = {
          detail: 'Invalid Server Response',
          code: INVALID_JSON_RESPONSE
        };
      }
    }

    // need to extract content of unexpected error
    const { detail, code } = error || {
      detail: 'Error reading server response',
      code: ERROR_READING_SERVER_RESPONSE
    };

    const apiError = new ApiError(detail, 400, code, response, requestArgs);
    throw apiError;
  }

  static async makeRequest({
    method,
    path,
    headers = {},
    payload = {},
    raw = false,
    withCredentials = false
  }) {
    const opts = {
      method,
      headers: ApiHelper.createHeaders(headers),
      withCredentials
    };

    if (Object.keys(payload).length > 0) {
      if (
        method === HttpMethod.POST ||
        method === HttpMethod.PUT ||
        method === HttpMethod.PATCH
      ) {
        opts.data = payload;
      }
    }

    if (raw) {
      opts.validateStatus = status => {
        return status < 500;
      };

      return axios(path, opts);
    }
    return axios(path, opts).then(ApiHelper.checkStatus.bind(null, opts));
  }
}

module.exports = ApiHelper;
