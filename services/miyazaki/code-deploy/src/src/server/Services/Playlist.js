const JsonApiHelper = require('../../common/JsonApiHelper');

async function getMovieDetail(ctx, titleId) {
  const opts = {
    path: `${ctx.state.env.DISCOVER_BASE_URL}/discover/titles/${titleId}`,
    payload: { page: 1, size: 10 }
  };
  return JsonApiHelper.get(opts);
}

module.exports = {
  getMovieDetail
};
