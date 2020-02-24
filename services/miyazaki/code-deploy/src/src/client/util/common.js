import currency from 'currency.js';
import get from 'lodash.get';
import { PORTRAIT_PRIMARY, LANDSCAPE_PRIMARY } from '../constants/common.js';

/**
 * If the URL is in HTTP convert it to HTTPS, otherwise return it as is.
 */
export const convertProtocolToHttps = url => {
  // only first match
  if (url.match(/^https:\/\//)) {
    return url;
  }

  // only first match
  return url.replace(/^http:\/\//, 'https://');
};

const priceformatter = (value, { code }) =>
  currency(value, {
    symbol: code,
    precision: 0,
    separator: '.',
    pattern: `! #`,
    formatWithSymbol: true
  });

export const currencyCodes = { ID: 'IDR', SG: 'SGD' };

/**
 * Format the price by the currency code.
 * If the value is 15000 and the currency code is IDR, the formatted value would be
 * IDR 15000
 * @param {number} value - The price
 * @param {object} options - The formatting options
 * @param {string} options.code - The currency code in ISO 4217
 * @returns {string} The formatted price
 */
export const formatPriceByCurrency = (value, options = {}) => {
  if (typeof value !== 'number') {
    throw new Error('The price value is required');
  }

  const { code } = options;
  if (code === undefined) {
    throw new Error(
      '`code` is undefined. The currency code in ISO 4217 is required'
    );
  }

  return priceformatter(value, { code }).format(true);
};

/**
 * @typedef {Object} Dimensions
 * @property {number} width - The screen width
 * @property {number} height - The screen height
 * @property {string} orientation - The screen orientation
 */
/**
 *
 * @param {Object} screen The window.screen object
 * @returns {Dimensions} The dimensions
 */
export const getScreenDimensions = screen => {
  const { width, height } = screen;
  const orientationType = get(screen, 'orientation.type', '');

  let orientation = orientationType;
  if (orientationType === '') {
    orientation = isPortrait(screen) ? PORTRAIT_PRIMARY : LANDSCAPE_PRIMARY;
  }

  return { width, height, orientation };
};

const isPortrait = screen => screen.height > screen.width;
