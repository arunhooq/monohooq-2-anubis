import get from 'lodash.get';
import querystring from 'querystring';

export const getQueryParams = uri => {
  const qs = uri.replace(/.*\?/, '');
  const params = querystring.decode(qs);
  return params;
};

export const getCurrentPage = routeURL => {
  const splittedURL = routeURL.split('/');
  const currentPage = get(splittedURL, '[1]', '');
  return currentPage === '' ? 'discover' : currentPage;
};
