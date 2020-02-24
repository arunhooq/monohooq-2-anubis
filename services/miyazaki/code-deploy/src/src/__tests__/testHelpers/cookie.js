export const SetupAccount = async (server, host = 'grab.hooq.local') => {
  const setupResponse = await server
    .get(`/setup?id_token=HOOQxGRAB`)
    .set('Host', host)
    .expect(302);

  const cookies = setupResponse.header['set-cookie'][0];
  const cookieParam = cookies.split('; ');
  return [cookieParam[0]];
};
