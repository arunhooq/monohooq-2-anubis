async function getPersonalizeMovie(ctx, titleId) {
  const opts = {
    path: `/personalize/titles/${titleId}`
  };
  return ctx.state.Api.get(opts);
}

async function getPersonalizeTVShow(ctx, titleId, seasonNum) {
  const opts = {
    path: `/personalize/titles/tvshow/${titleId}/season/${seasonNum}`
  };
  return ctx.state.Api.get(opts);
}

module.exports = {
  getPersonalizeMovie,
  getPersonalizeTVShow
};
