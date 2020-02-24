const redis = require('../../connections/redis');
const uuid = require('uuid/v4');

async function getPayload(key) {
  try {
    const payload = await redis.getAsync(key);
    return JSON.parse(payload);
  } catch (err) {
    throw err;
  }
}

async function storeRedirectionProperties(ctx, payload = {}) {
  const redirectId = uuid().toString();
  const key = `${ctx.state.redisKey}:redirect:${redirectId}`;
  await redis.setAsync(key, JSON.stringify(payload));
  return redirectId;
}

module.exports = {
  getPayload,
  storeRedirectionProperties
};
