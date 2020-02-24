const JwtParser = require('../Util/JwtParser');
const JsonApiHelper = require('../../common/JsonApiHelper');

async function getWatchedMovies(ctx) {
  const opts = {
    path: '/playlist/watched',
    payload: { page: 1, size: 10, scope: 'detail' }
  };
  return ctx.state.Api.get(opts);
}

async function updateContact(ctx) {
  const parsedToken = JwtParser(ctx.state.Api.sessionToken);
  const payload = {
    spAccountID: parsedToken.sid,
    contactID: parsedToken.cid,
    country: parsedToken.loc
  };

  return await JsonApiHelper.put({
    path: `${ctx.state.env.EVE_BASE_URL}/1.0/webhook/contact`,
    payload
  });
}

async function getPlaylistHistory(ctx) {
  const opts = {
    path: '/playlist/history',
    payload: { scope: 'detail' }
  };
  return ctx.state.Api.get(opts);
}

module.exports = {
  getWatchedMovies,
  updateContact,
  getPlaylistHistory
};
