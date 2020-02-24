/* eslint-env jest */

import {
  convertProtocolToHttps,
  formatPriceByCurrency,
  getScreenDimensions
} from '../../../client/util/common';

describe.only('convertProtocolToHttps', () => {
  it('if the URL is in HTTPS then return it as is', () => {
    const imageUrl =
      'https://m-images.hooq.tv/resizer/resizer?service=singtel&surl=https://hooq-sanctuary.s3.amazonaws.com/assets/catalogs/SPOTLIGHT/5a301e86cdec.jpg?';
    const result = convertProtocolToHttps(imageUrl);
    const expectedValue =
      'https://m-images.hooq.tv/resizer/resizer?service=singtel&surl=https://hooq-sanctuary.s3.amazonaws.com/assets/catalogs/SPOTLIGHT/5a301e86cdec.jpg?';

    expect(result).toEqual(expectedValue);
  });

  it('if the URL is in HTTP then convert it to HTTPS', () => {
    const imageUrl =
      'http://m-images.hooq.tv/resizer/resizer?service=singtel&surl=https://hooq-sanctuary.s3.amazonaws.com/assets/catalogs/SPOTLIGHT/5a301e86cdec.jpg?';
    const result = convertProtocolToHttps(imageUrl);
    const expectedValue =
      'https://m-images.hooq.tv/resizer/resizer?service=singtel&surl=https://hooq-sanctuary.s3.amazonaws.com/assets/catalogs/SPOTLIGHT/5a301e86cdec.jpg?';

    expect(result).toEqual(expectedValue);
  });
});

describe('formatPriceByCurrency', () => {
  it('if the value is not specified should throw an Error', () => {
    try {
      formatPriceByCurrency();
    } catch (e) {
      expect(e.message).toBe('The price value is required');
    }
  });

  it('if the code in the options is not specified should throw an Error', () => {
    try {
      formatPriceByCurrency(50000);
    } catch (e) {
      expect(e.message).toBe(
        '`code` is undefined. The currency code in ISO 4217 is required'
      );
    }
  });

  it('specifying the required params should return formatted the price', () => {
    const result = formatPriceByCurrency(50000, {
      code: 'IDR'
    });
    const expectedValue = 'IDR 50.000';
    expect(result).toEqual(expectedValue);
  });
});

describe('getScreenDimensions', () => {
  it('should return an object contains the height and width', () => {
    // stub the inner width and height
    global.window = {
      screen: {
        width: 320,
        height: 640,
        orientation: {
          type: 'portrait-primary'
        }
      }
    };
    const result = getScreenDimensions(global.window.screen);
    expect(result.width).toEqual(320);
    expect(result.height).toEqual(640);
    expect(result.orientation).toEqual('portrait-primary');

    // restore the window
    global.window = undefined;
  });
});
