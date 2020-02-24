import { get as lodashGet } from 'lodash';

const getCookies = cookies => {
  let list = {};
  cookies &&
    cookies.split(';').forEach(function(cookie) {
      const parts = cookie.split('=');
      list[parts[0].trim()] = parts[1].trim();
    });
  return list;
};

export const set = (key, data) => {
  const item = JSON.stringify(data);
  document.cookie = `${key}=${item};path=/`;
};

export const get = (key, fallback) => {
  const cookies = getCookies(document.cookie);
  const data = lodashGet(cookies, key) ? JSON.parse(cookies[key]) : null;
  if (!data && fallback) {
    return fallback;
  }
  return data;
};

export const remove = key => {
  document.cookie = key + '=;path=/;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

export default { set, get, remove };
