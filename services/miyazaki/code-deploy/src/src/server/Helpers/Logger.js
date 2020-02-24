const newrelic = require('newrelic');
const flat = require('flat');
const pino = require('pino')({
  name: 'HOOQ - webview',
  safe: true,
  messageKey: 'message'
});

const suffix = process.env.ENV === 'production' ? '' : `${process.env.ENV}`;
const eventName = `HooqWebviewEvent${suffix.charAt(0).toUpperCase() +
  suffix.slice(1)}`;
const QUIET_LOG = process.env.QUIET_LOG === 'true';

module.exports = {
  info,
  debug,
  error
};

const makeKey = str => {
  if (str.indexOf('.') < 0) {
    return str;
  }

  let key = '';
  str.split('.').forEach((el, i) => {
    key += i > 0 ? el[0].toUpperCase() + el.substr(1) : el;
  });

  return key;
};

/**
 * flatten is a function to make the
 * nested object as one key and separated
 * by dot at makeKey function
 *
 * @param {object} obj
 */
const flatten = obj => {
  const flattened = flat(obj);
  const ret = {};
  for (let key in flattened) {
    ret[makeKey(key)] = flattened[key];
  }
  return ret;
};

/**
 * Record any info for logging. This function save
 * to pino and send to newrelic
 *
 * @param {string} action
 * @param {object} payload
 */
function info(action = 'unknown', payload = {}) {
  if (!QUIET_LOG) {
    const obj = Object.assign({ action }, payload);
    const flattenObject = flatten(obj);
    pino.info(obj);
    newrelic.recordCustomEvent(eventName, flattenObject);
  }
}

/**
 * Record any info for logging. This function only save
 * to pino
 *
 * @param {object} obj
 */
function debug(obj) {
  if (!QUIET_LOG) {
    pino.info(obj);
  }
}

/**
 * Record any error for logging. This function only save
 * to pino and send to newrelic
 *
 * @param {object} obj
 */
function error(action = 'unknown', payload = {}) {
  if (!QUIET_LOG) {
    const obj = Object.assign({ action }, payload);
    const flattenObject = flatten(obj);
    pino.error(obj, action);
    newrelic.recordCustomEvent(eventName, flattenObject);
  }
}
