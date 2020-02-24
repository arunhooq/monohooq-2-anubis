// FIXME
const axios = require('axios');
const querystring = require('querystring');

test.skip('Discover API is accessible', async () => {
  const response = await axios.get(
    'https://cdn-discover.hooq.tv/',
    querystring.stringify({
      region: 'ID',
      page: 1,
      perPage: 25,
      platform: 'WEBCLIENT'
    })
  );

  expect(response.status).toBe(200);
});

test.skip('EV WEBVIEW is accessible', async () => {
  const response = await axios.get('https://authenticate.hooq.tv');

  expect(response.status).toBe(200);
});

test.skip('EVE is accessible', async () => {
  const response = await axios.get('http://eve.hooq.vpc');

  // we will receive the following error "getaddrinfo ENOTFOUND eve.hooq.vpc eve.hooq.vpc:80" in the non staging environment
  expect(response.status).toBe(200);
});

test.skip('Kashmir is accessible', async () => {
  const response = await axios.get('http://kashmir.hooq.vpc/2.0');

  // we will receive the following error "getaddrinfo ENOTFOUND kashmir.hooq.vpc kashmir.hooq.vpc:80" in the non staging environment
  expect(response.status).toBe(200);
});

test.skip('Search API is accessible', async () => {
  const response = await axios.get(
    'https://api.hooq.tv/2.0/search/browse?region=ID',
    querystring.stringify({
      region: 'ID'
    })
  );

  expect(response.status).toBe(200);
});
