// FIXME
const HOOQApi = require('./../../../server/Services/HooqApi');

test.skip('should be able to load the module', () => {
  expect(HOOQApi).toBeDefined();
});

test.skip('should get 200 from healthcheck from API Gateway', async () => {
  const Api = new HOOQApi({
    clientHeaders: {},
    apikey: '',
    deviceID: '',
    deviceSignature: '',
    deviceOS: '',
    deviceOSVersion: '',
    deviceModel: '',
    sessionToken: ''
  });

  const opts = {
    path: '/partner/health'
  };

  const resp = await Api.get(opts);
  expect(resp).toBeDefined();
});

test.skip('should get 200 from healthcheck from API Gateway with client headers', async () => {
  const Api = new HOOQApi({
    clientHeaders: {
      'user-agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36'
    },
    apikey: '12345'
  });

  const opts = {
    path: '/partner/health'
  };

  const resp = await Api.get(opts);
  expect(resp).toBeDefined();
});
