const { updateContact } = require('../../Services/Sanctuary');
const { INVALID_SESSION } = require('../../../common/ErrorCodes');

async function putPlay(ctx, next) {
  const { session } = ctx.state;
  if (!session) {
    throw new ApiError(
      INVALID_SESSION.message,
      INVALID_SESSION.statusCode,
      INVALID_SESSION.errorCode
    );
  }

  const titleId = ctx.state.title.id;
  const { length, position } = ctx.state.request.body;

  try {
    const response = await ctx.state.Api.put({
      path: `/catalog/play/${titleId}`,
      payload: {
        length,
        position
      }
    });

    return ctx.json({}, 204);
  } catch (err) {
    // if account is not in sanctuary
    if (err.errorCode === '2048' && err.statusCode === 400) {
      await updateContact(ctx);
      return ctx.json({}, 204);
    }

    throw err;
  }
}

module.exports = {
  putPlay
};
