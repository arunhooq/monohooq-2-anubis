import { Router } from '../routes';
import logger from './../../common/ClientLogger';

/**
 * Redirect to error page.
 *
 * @param {boolean} isServer
 * @param {Object} res
 * @param {Object} params
 * @param {Object} error An error object
 */
export const redirectErrorPage = (isServer, res, params, error) => {
  if (error instanceof Error === false) {
    throw new Error(`The {error} parameter should be an instance of an Error`);
  }

  if (error) {
    logger.error(error);
  }

  if (isServer) {
    let url = '/error';
    Object.keys(params).forEach(key => {
      url = `${url}${key}=${params[key]}&`;
    });
    url = url.substr(0, url.length - 1);
    res.writeHead(302, { Location: url });
    res.end();
  } else {
    params.code = error.code;
    params.message = error.message;
    Router.pushRoute('_error', params);
  }
};
