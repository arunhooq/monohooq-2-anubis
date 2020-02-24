import { generateMinifiedNREUM } from '../../../client/helpers/newrelicUtil';

jest.mock('next/config', () => {
  return jest.fn().mockImplementation(() => {
    return {
      publicRuntimeConfig: {
        NODE_ENV: 'test',
        ATTEMPT_TO_SHOW: 10,
        INTERVAL_TO_SHOW: 3000,
        ENABLE_NEWRELIC: 'false',
        NEWRELIC_BROWSER_APPLICATION_ID: '12345',
        NEWRELIC_BROWSER_LICENSE_KEY: '123',
        NEWRELIC_BROWSER_FILE_PATH: '/static/nr/nr.js',
        CONVIVA_CORE_SDK_PATH: '/static/conviva/conviva-core-sdk.min.js',
        CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH:
          '/static/conviva/conviva-html5native-impl.js',
        DISCOVER_BASE_URL: 'https://cdn-discover.hooq.tv/v1.5',
        CHANNEL_BASE_URL: 'https://cdn-discover.hooq.tv/v1.5',
        PLAY_BASE_URL: 'https://api.hooq.tv',
        SEARCH_BASE_URL: 'https://api.hooq.tv',
        EV_WEBVIEW_BASE_URL: 'http://eve-nightly.hooq.vpc',
        STAGING_TITLE_ID: 'ddb672cc-134f-460a-a89e-12e35eb426c7',
        STAGING_DISCOVER_BASE_URL: 'https://cdn-discover.hooq.tv/v1.1',
        npm_package_version: process.env.npm_package_version
      }
    };
  });
});

describe('newrelicUtil: ENABLE_NEWRELIC is set to false', () => {
  describe('generateMinifiedNREUM', () => {
    it('if ENABLE_NEWRELIC env is set to false then it will return empty string', () => {
      const result = generateMinifiedNREUM();
      const expectedValue = '';
      expect(result).toEqual(expectedValue);
    });

    it('if ENABLE_NEWRELIC env is set to false then it will return NREUM string', () => {
      process.env.ENABLE_NEWRELIC = 'false';
      const result = generateMinifiedNREUM();
      const expectedValue = ``;

      expect(result).toEqual(expectedValue);
    });
  });
});
