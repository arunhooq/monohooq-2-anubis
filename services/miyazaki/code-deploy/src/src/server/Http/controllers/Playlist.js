const ApiError = require('../../Errors/ApiError');
const Constants = require('../../../common/General');
const { FAILED_GETTING_HISTORY } = require('../../../common/ErrorCodes');
const get = require('lodash.get');
const {
  getWatchedMovies,
  updateContact,
  getPlaylistHistory
} = require('../../Services/Sanctuary');
const {
  calculateProgress,
  calculateTimeLeft
} = require('../../Helpers/Playlist');

const { CollectionTitleType } = Constants;

async function getRecentPlay(ctx, next) {
  try {
    const history = await getWatchedMovies(ctx);
    const historyData = history.data || [];
    const mappedHistory = historyData
      .filter(movie => movie.attributes)
      .map(movie => {
        const {
          title,
          position,
          duration,
          as,
          availability,
          images
        } = movie.attributes;
        const movieResult = {
          id: movie.id,
          title,
          position,
          duration,
          as,
          availability,
          timeLeft: calculateTimeLeft(movie) || 0,
          percentage: calculateProgress(movie) || 0,
          images: images || [],
          lastWatched: get(movie, 'attributes.lastWatched', null)
        };
        if (movie.type === CollectionTitleType.TVSHOW) {
          movieResult.season = get(movie, 'attributes.lastWatched.season');
          movieResult.episode = get(movie, 'attributes.lastWatched.episode');
        }

        return movieResult;
      });

    return ctx.json({ data: mappedHistory });
  } catch (err) {
    // if account is not in sanctuary
    if (err.errorCode === '2048' && err.statusCode === 400) {
      await updateContact(ctx);
    }
    return ctx.json({ data: [] });
  }
}

async function getHistory(ctx, next) {
  try {
    await getPlaylistHistory(ctx);
    return ctx.json({ data: [] });
  } catch (err) {
    throw new ApiError(
      err,
      FAILED_GETTING_HISTORY.statusCode,
      FAILED_GETTING_HISTORY.errorCode
    );
  }
}

module.exports = {
  getRecentPlay,
  getHistory
};
