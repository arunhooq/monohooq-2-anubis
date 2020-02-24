const JsonApiHelper = require('../../common/JsonApiHelper');
const { getApiGatewayHost, getServerPath } = require('../Util/ApiUtil');
const jwtDecode = require('jwt-decode');
const retry = require('promise-retry');
const TOKEN_EXPIRY_BUFFER = 30;

class HOOQApi {
  constructor({
    clientHeaders = {},
    deviceType = null,
    deviceId = null,
    deviceSignature = null,
    deviceOS = null,
    deviceOSVersion = null,
    deviceModel = null,
    apikey = null,
    sessionToken = null,
    refreshToken = null,
    xRealIp = null,
    requestId = null
  } = {}) {
    this.clientHeaders = clientHeaders;
    this.deviceType = deviceType;
    this.deviceId = deviceId;
    this.deviceSignature = deviceSignature;
    this.deviceOS = deviceOS;
    this.deviceOSVersion = deviceOSVersion;
    this.deviceModel = deviceModel;
    this.apikey = apikey;
    this.sessionToken = sessionToken;
    this.refreshToken = refreshToken;
    this.host = getApiGatewayHost();
    this.xRealIp = xRealIp;
    this.isApiGateway = true;
    this._baseUrl = null;
    this.requestId = requestId;
  }

  get baseUrl() {
    if (!this._baseUrl) {
      throw new Error(
        'this._baseUrl is not defined. Value for this._baseUrl should be set in constructor'
      );
    }
    return this._baseUrl;
  }

  setSessionToken(token) {
    this.sessionToken = token;
  }

  setRefreshToken(token) {
    this.refreshToken = token;
  }

  setDeviceSignature(deviceSignature) {
    this.deviceSignature = deviceSignature;
  }

  isTokenExpiredError(err) {
    return (
      err.statusCode === 400 &&
      err.errorCode.includes('Kong-404') &&
      err.message.toString().includes('Token expired')
    );
  }

  // Kong requires api key for all requests
  getDefaultHeaders(headers = {}) {
    const apikey = this.apikey;
    return {
      ...headers,
      apikey,
      'X-Request-Id': this.requestId
    };
  }

  enhanceHeaders(headers = {}) {
    const enHeaders = {
      ...headers,
      Accept: 'application/json',
      'User-Agent': this.clientHeaders['user-agent'],
      'x-Device-Type': this.deviceType,
      'X-Real-IP': this.xRealIp,
      'X-Forwarded-For': this.xRealIp,
      'X-Device-Id': this.deviceId,
      'X-Device-Os': this.deviceOS,
      'X-Device-Os-Version': this.deviceOSVersion,
      'X-Device-Model': this.deviceModel
    };

    if (this.deviceSignature) {
      enHeaders['Device-Signature'] = this.deviceSignature;
    }

    if (this.sessionToken) {
      enHeaders['Authorization'] = `Bearer ${this.sessionToken}`;
    }

    return enHeaders;
  }

  async get({
    path,
    headers = {},
    payload = {},
    raw = false,
    customApiVersion = null,
    enhanceHeaders = true
  }) {
    const serverPath = getServerPath(path, {
      isApiGateway: this.isApiGateway,
      customApiVersion
    });

    return retry(
      (retryFn, number) => {
        const requestHeaders = this.getDefaultHeaders(headers);
        const opts = {
          path: serverPath,
          headers: enhanceHeaders
            ? this.enhanceHeaders(requestHeaders)
            : requestHeaders,
          payload,
          raw
        };
        return JsonApiHelper.get(opts).catch(async err => {
          if (this.isTokenExpiredError(err)) {
            await this.updateToken(this.refreshToken);
            retryFn(err);
          }

          throw err;
        });
      },
      {
        retries: 3
      }
    )
      .then(value => {
        return value;
      })
      .catch(err => {
        throw err;
      });
  }

  async post({
    path,
    headers = {},
    payload = {},
    raw = false,
    customApiVersion = null,
    enhanceHeaders = true
  }) {
    const serverPath = getServerPath(path, {
      isApiGateway: this.isApiGateway,
      customApiVersion
    });

    return retry(
      (retryFn, number) => {
        const requestHeaders = this.getDefaultHeaders(headers);
        const opts = {
          path: serverPath,
          headers: enhanceHeaders
            ? this.enhanceHeaders(requestHeaders)
            : requestHeaders,
          payload,
          raw
        };
        return JsonApiHelper.post(opts).catch(async err => {
          if (this.isTokenExpiredError(err)) {
            await this.updateToken(this.refreshToken);
            retryFn(err);
          }

          throw err;
        });
      },
      {
        retries: 3
      }
    )
      .then(value => {
        return value;
      })
      .catch(err => {
        throw err;
      });
  }

  async put({
    path,
    headers = {},
    payload = {},
    raw = false,
    customApiVersion = null,
    enhanceHeaders = true
  }) {
    const serverPath = getServerPath(path, {
      isApiGateway: this.isApiGateway,
      customApiVersion
    });

    return retry(
      (retryFn, number) => {
        const requestHeaders = this.getDefaultHeaders(headers);
        const opts = {
          path: serverPath,
          headers: enhanceHeaders
            ? this.enhanceHeaders(requestHeaders)
            : requestHeaders,
          payload,
          raw
        };
        return JsonApiHelper.put(opts).catch(async err => {
          if (this.isTokenExpiredError(err)) {
            await this.updateToken(this.refreshToken);
            retryFn(err);
          }

          throw err;
        });
      },
      {
        retries: 3
      }
    )
      .then(value => {
        return value;
      })
      .catch(err => {
        throw err;
      });
  }

  isTokenExpired() {
    const decoded = jwtDecode(this.sessionToken);
    const tokenExpiry = decoded.exp;
    const limitExpiryInMs = (tokenExpiry - TOKEN_EXPIRY_BUFFER) * 1000;
    return Date.now() >= limitExpiryInMs;
  }

  async authRefreshToken(refreshToken) {
    // TODO: Do we need to check the response of refresh token endpoint?
    return this.post({
      path: '/user/token/refresh',
      payload: {
        data: {
          refreshToken,
          ipAddress: this.xRealIp
        }
      },
      enhanceHeaders: true
    });
  }

  async getVisitorToken() {
    // TODO: Do we need to check the response of refresh token endpoint?
    return this.post({
      path: '/visitor/activate',
      enhanceHeaders: true
    });
  }

  async authRefreshToken(refreshToken) {
    // TODO: Do we need to check the response of refresh token endpoint?
    return this.post({
      path: '/user/token/refresh',
      payload: {
        data: {
          refreshToken,
          ipAddress: this.xRealIp
        }
      },
      enhanceHeaders: true
    });
  }

  async updateToken(refreshToken) {
    let response;
    try {
      response = await this.authRefreshToken(refreshToken);
    } catch (error) {
      // TODO it should be fixed in the future
      throw error;
    }
    this.sessionToken = response.data.accessToken;
    this.refreshToken = response.data.refreshToken;

    return { accessToken: this.sessionToken, refreshToken: this.refreshToken };
  }

  async updateVisitorToken() {
    let response;
    try {
      response = await this.getVisitorToken();
    } catch (error) {
      // TODO it should be fixed in the future
      throw error;
    }
    this.sessionToken = response.data.accessToken;
    this.refreshToken = response.data.refreshToken;

    return { accessToken: this.sessionToken, refreshToken: this.refreshToken };
  }
}

module.exports = HOOQApi;
