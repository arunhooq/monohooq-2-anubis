const ApiError = require('../../Errors/ApiError');
const { INVALID_SESSION } = require('../../../common/ErrorCodes');

async function getTvPlay(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  try {
    let { channelId } = ctx.params;

    const response = await ctx.state.Api.get({
      path: `/play/tv/${channelId}`
    });

    return ctx.json(
      {
        id: response.id,
        logo_url: response.logo_url,
        title: response.title,
        description: response.description,
        is_premium: response.is_premium,
        provider: response.streams[0].label,
        playback_url: response.streams[0].stream_url
      },
      200
    );
  } catch (err) {
    throw err;
  }
}

module.exports = {
  getTvPlay
};
