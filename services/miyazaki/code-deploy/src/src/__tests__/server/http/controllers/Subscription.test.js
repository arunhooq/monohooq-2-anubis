// FIXME
const next = require('next');
const supertest = require('supertest');
const { bootstrap } = require('../../../../bootstrap');
const { SetupAccount } = require('../../../testHelpers/cookie');

const dev = process.env.NODE_ENV !== 'production';
const app = next({ dir: './src/client', dev });
const handle = app.getRequestHandler();
const SKU = 'ID-GRAB-ONETIME90D-GRAB'; // TODO: should test all SKUs when all SKUs are ready to be used

jest.setTimeout(30000);

describe.skip('SubscriptionController', function() {
  let server;
  let cookie;

  beforeAll(async () => {
    server = supertest(bootstrap(app, handle).listen());
  });

  beforeEach(async () => {
    cookie = await SetupAccount(server);
  });

  describe('/subscription/status', () => {
    it('should return "NEW_USER" when user is not activated', async () => {
      const subscriptionStatusResponse = await server
        .get(`/subscription/status`)
        .set('Cookie', cookie)
        .expect(302);

      expect(subscriptionStatusResponse.body).toBeDefined();
    });

    it('should return "ACTIVE" when user is already activated', async () => {
      const activateResponse = await server
        .post(`/activate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(activateResponse.body).toBeDefined();

      const subscriptionStatusResponse = await server
        .get(`/subscription/status`)
        .set('Cookie', cookie)
        .expect(302);

      expect(subscriptionStatusResponse.body).toBeDefined();
    });
  });

  describe('/subscription/deactivate', () => {
    it("should successfully deactivate user's SKU", async () => {
      const activateResponse = await server
        .post(`/activate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(activateResponse.body).toBeDefined();

      const deactivateResponse = await server
        .post(`/subscription/deactivate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(deactivateResponse.body).toBeDefined();
    });

    it('should return 400 when user does not have orders with provided SKU', async () => {
      const deactivateResponse = await server
        .post(`/subscription/deactivate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(deactivateResponse.body).toBeDefined();
    });

    it('should change "Active" status into "Final Bill" after deactivating user\'s SKU', async () => {
      const activateResponse = await server
        .post(`/activate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(activateResponse.body).toBeDefined();

      const deactivateResponse = await server
        .post(`/subscription/deactivate`)
        .send({ sku: SKU })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(302);

      expect(deactivateResponse.body).toBeDefined();

      const subscriptionStatusResponse = await server
        .get(`/subscription/status`)
        .set('Cookie', cookie)
        .expect(302);

      expect(subscriptionStatusResponse.body).toBeDefined();
    });
  });

  describe('/subscription/sku', () => {
    it('should return list of SKU', async () => {
      const skuListResponse = await server
        .post(`/subscription/sku`)
        .send({
          titleId: '859ce6f8-0af9-4958-9a3b-162477ddee62'
        })
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(404);

      expect(skuListResponse.body).toBeDefined();
    });

    it('should return 400 when parameters are missing', async () => {
      const skuListResponse = await server
        .post(`/subscription/sku`)
        .set('Accept', 'application/json')
        .set('Cookie', cookie)
        .expect(404);
    });
  });
});
