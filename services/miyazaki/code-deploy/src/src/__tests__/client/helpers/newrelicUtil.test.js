import { generateMinifiedNREUM } from '../../../client/helpers/newrelicUtil';

describe.skip('newrelicUtil', () => {
  describe('generateMinifiedNREUM', () => {
    it('if ENABLE_NEWRELIC env is set to false then it will return empty string', () => {
      const result = generateMinifiedNREUM();
      const expectedValue = '';
      expect(result).toEqual(expectedValue);
    });

    it('if ENABLE_NEWRELIC env is set to false then it will return NREUM string', () => {
      process.env.ENABLE_NEWRELIC = 'true';
      const result = generateMinifiedNREUM();
      const expectedValue = ``;

      expect(result).toEqual(expectedValue);
    });

    it('if the required env for New Relic Browser agent is set then return NREUM string', () => {
      process.env.ENABLE_NEWRELIC = 'true';
      process.env.NEWRELIC_BROWSER_APPLICATION_ID = '12345';
      process.env.NEWRELIC_BROWSER_LICENSE_KEY = '123';
      const result = generateMinifiedNREUM();
      const expectedValue = `window.NREUM||(NREUM={}),window.NREUM.info={licenseKey:"123",applicationID:"12345",sa:1};`;

      expect(result).toEqual(expectedValue);
    });
  });
});
