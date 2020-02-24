// FIXME
const next = require('next');
const supertest = require('supertest');
const { bootstrap } = require('../../../../bootstrap');

const { SetupAccount } = require('../../../testHelpers/cookie');

const dev = process.env.NODE_ENV !== 'production';
const app = next({ dir: './src/client', dev });
const handle = app.getRequestHandler();
const SKU = 'ID-PAID01-ONETIME-GRAB-REWARDS'; // TODO: should test all SKUs when all SKUs are ready to be used

jest.setTimeout(30000);

describe.skip('Play Endpoint Test', () => {
  let server;
  let cookie;

  beforeAll(async () => {
    server = supertest(bootstrap(app, handle).listen());
  });

  beforeEach(async () => {
    cookie = await SetupAccount(server);
  });

  it('should successfully get manifest and license', async () => {
    const titleId = '3B086D5A-AD29-43DD-ADDA-492AC07713B8';

    const activateResponse = await server
      .post(`/activate`)
      .send({ sku: SKU })
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(activateResponse.body).toBeDefined();

    const playResponse = await server
      .get(`/play/${titleId}`)
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(playResponse.body).toBeDefined();
  });

  it('should successfully get manifest and license after normal deactivate', async () => {
    const titleId = '3B086D5A-AD29-43DD-ADDA-492AC07713B8';

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

    const playResponse = await server
      .get(`/play/${titleId}`)
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(playResponse.body).toBeDefined();
  });

  it('should got error since user is not eligible because immediate deactivate', async () => {
    const titleId = '3B086D5A-AD29-43DD-ADDA-492AC07713B8';
    const now = new Date();

    const activateResponse = await server
      .post(`/activate`)
      .send({ sku: SKU })
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(activateResponse.body).toBeDefined();

    const deactivateResponse = await server
      .post(`/subscription/deactivate`)
      .send({ sku: SKU, endDate: now.getTime() })
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(deactivateResponse.body).toBeDefined();

    const statusResponse = await server
      .get(`/subscription/status`)
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(statusResponse.body).toBeDefined();

    const playResponse = await server
      .get(`/play/${titleId}`)
      .set('Accept', 'application/json')
      .set('Cookie', cookie)
      .expect(302);

    expect(playResponse.body).toBeDefined();
  });
});
