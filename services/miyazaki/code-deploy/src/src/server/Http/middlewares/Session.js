const redis = require('../../../connections/redis');
const { setToOutput } = require('../../Util/Infer');
const JwtParser = require('../../Util/JwtParser');

module.exports = async function getSession(ctx, next) {
  const sessionConfig =
    ctx.state.partnerConfiguration.CustomConfig.middlewares.session;
  const sessionId = ctx.state.cookies[sessionConfig.cookieKey];
  ctx.state.session = null;

  const { skipCheckSession } = ctx.query;
  const shouldSkipCheckSession = skipCheckSession === 'true' ? true : false;

  if (sessionId && !shouldSkipCheckSession) {
    ctx.state.sessionId = sessionId;
    const key = `${ctx.state.redisKey}:session:${ctx.state.sessionId}`;
    const redisData = await redis.getAsync(key);
    const sessionData = JSON.parse(redisData);

    if (sessionData) {
      const jwtPayload = JwtParser(sessionData.session.accessToken);
      if (jwtPayload.loc !== ctx.state.geo.country) {
        ctx.state.shouldRefreshToken = true;
      }

      const redactedSessionData = {
        ...sessionData,
        ...{
          session: {
            accessToken: sessionData.session.accessToken
          }
        }
      };
      const webSessionData = (({ partnerToken, ...others }) => ({
        ...others
      }))(redactedSessionData);
      const webOutput = getWebOutput(sessionConfig.output);

      ctx.state.isVisitor = sessionData.isVisitor;
      setToOutput(ctx, sessionConfig.output, sessionData);
      setToOutput(ctx, webOutput, webSessionData);
    }
  }

  await next();
};

function getWebOutput(output) {
  const key = output.replace('@', '');
  const webOutput = `@web${key.charAt(0).toUpperCase()}${key.slice(1)}`;
  return webOutput;
}
