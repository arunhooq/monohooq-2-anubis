// FIXME
const next = require('next');
const supertest = require('supertest');
const { bootstrap } = require('../../../../bootstrap');
const { SetupAccount } = require('../../../testHelpers/cookie');
const dev = process.env.NODE_ENV !== 'production';
const app = next({ dir: './src/client', dev });
const handle = app.getRequestHandler();

jest.setTimeout(30000);

describe.skip('ConfigController', () => {
  let server;
  let cookie;
  const titleId = 'dummy-title-id';

  beforeAll(async () => {
    server = supertest(bootstrap(app, handle).listen());
  });

  beforeEach(async () => {
    cookie = await SetupAccount(server);
  });

  describe('/config/plans', () => {
    test('should return plan configuration as an object with 302 status code', async () => {
      const planResponse = await server
        .post(`/config/plans`)
        .send({ titleId })
        .set('Cookie', cookie)
        .expect(302);

      expect(planResponse.body).toBeDefined();
    });

    test('should got `Missing required parameters` error message when titleId is missing', async () => {
      const missingResponse = await server
        .post(`/config/plans`)
        .set('Cookie', cookie)
        .expect(302);

      expect(missingResponse.body).toBeDefined();
    });
  });
});
