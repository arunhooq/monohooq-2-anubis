// FIXME
const next = require('next');
const supertest = require('supertest');
const { bootstrap } = require('../../../../bootstrap');
const { SetupAccount } = require('../../../testHelpers/cookie');
const JsonApiHelper = require('../../../../common/JsonApiHelper');
const {
  getValidPath,
  getPrimaryId
} = require('../../../../server/Http/controllers/Setup');
const { mockedContext } = require('../../../../__mocks__/server/context');
const {
  mockedRedirectPages
} = require('../../../../__mocks__/server/http/controllers/Setup');
const dev = process.env.NODE_ENV !== 'production';
const app = next({ dir: './src/client', dev });
const handle = app.getRequestHandler();

describe.skip('SetupController', () => {
  let server;
  let cookie;

  beforeAll(async () => {
    server = supertest(bootstrap(app, handle).listen());
  });

  describe('Grab Rails', () => {
    test('should not have query params in id-id-rail', async () => {
      const opts = { path: `https://grab-static.hooq.tv/id-id-rail.json` };
      const cards = await JsonApiHelper.get(opts);
      for (let card of cards) {
        const uri = new URL(card.cta_link);
        expect(uri.search).toEqual('');
      }
    });

    test('should not have query params in id-en-rail', async () => {
      const opts = { path: `https://grab-static.hooq.tv/id-en-rail.json` };
      const cards = await JsonApiHelper.get(opts);
      for (let card of cards) {
        const uri = new URL(card.cta_link);
        expect(uri.search).toEqual('');
      }
    });

    test('should not have query params in sg-en-rail', async () => {
      const opts = { path: `https://grab-static.hooq.tv/sg-en-rail.json` };
      const cards = await JsonApiHelper.get(opts);
      for (let card of cards) {
        const uri = new URL(card.cta_link);
        expect(uri.search).toEqual('');
      }
    });
  });

  describe('getValidPath', () => {
    test('should return a valid path based on redirect page in setup url', () => {
      for (let redirectPage of mockedRedirectPages) {
        const validPath = getValidPath(redirectPage.path);
        expect(validPath).toEqual(redirectPage.validPath);
      }
    });
  });

  describe('Setup account for several local environment', () => {
    test('grab.hooq.local', async () => {
      const host = 'grab.hooq.local';
      cookie = await SetupAccount(server, host);
      expect(cookie[0]).toContain('GRAB_SESSION');
    });

    test('sg-grab.hooq.local', async () => {
      const host = 'sg-grab.hooq.local';
      cookie = await SetupAccount(server, host);
      expect(cookie[0]).toContain('SG_GRAB_SESSION');
    });
  });

  describe('getPrimaryId', () => {
    test('should return undefined for `primaryId.cpCustomerId`', () => {
      const primaryId = getPrimaryId(mockedContext);
      expect(primaryId.cpCustomerId).toBeUndefined();
    });
  });
});
