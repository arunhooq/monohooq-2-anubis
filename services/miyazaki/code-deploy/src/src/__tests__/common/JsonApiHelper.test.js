const JsonApiHelper = require('../../common/JsonApiHelper');
test('should be able to load the module', () => {
  expect(JsonApiHelper).toBeDefined();
});

test('should call using GET', async () => {
  const opts = {
    path: 'http://httpbin.org/anything',
    headers: {
      foo: 'bar'
    },
    payload: {
      hooq: 'true'
    }
  };

  const resp = await JsonApiHelper.get(opts);
  expect(resp.url).toEqual('http://httpbin.org/anything?hooq=true');
});

test('should call using GET and return 400', async () => {
  const opts = {
    path: 'http://httpbin.org/status/400',
    headers: {
      foo: 'bar'
    },
    payload: {
      hooq: 'true'
    }
  };

  try {
    await JsonApiHelper.get(opts);
  } catch (err) {
    expect(err.response.status).toBe(400);
    expect(err.response.statusText).toEqual('BAD REQUEST');
  }
});
