const SessionKey = require('../../Util/SessionKey');
const WebError = require('../../Errors/WebError');
const ApiHelper = require('../../../common/ApiHelper');
const { deepCompile } = require('asura-helpers/value');
const { setToOutput } = require('../../Util/Infer');
const { get } = require('lodash');
const {
  UNVERIFIED_ACCOUNT,
  GENERIC_ERROR
} = require('../../../common/ErrorCodes');

function validateRestriction(ctx, restriction) {
  const { mandatory } = restriction;
  mandatory.forEach(e => {
    if (!get(ctx, e)) {
      throw new WebError(
        GENERIC_ERROR.message,
        GENERIC_ERROR.statusCode,
        GENERIC_ERROR.errorCode,
        GENERIC_ERROR.message,
        false,
        false,
        true
      );
    }
  });
}

function isStagingAccount(authConfig, token) {
  return token === authConfig.stagingToken.value;
}

function isError(response) {
  if (response.status >= 200 && response.status < 300) {
    return false;
  }

  return true;
}

module.exports = async (ctx, next) => {
  try {
    const authorizationConfig =
      ctx.state.partnerConfiguration.CustomConfig.middlewares.authorization;
    validateRestriction(ctx, authorizationConfig.restriction);

    if (!get(ctx.state, authorizationConfig.authentication.position, false)) {
      const primaryId = deepCompile(ctx, authorizationConfig.primaryId);
      ctx.state.sessionId = SessionKey.create(primaryId, ctx.state.env.KEY);
      return next();
    }

    // if staging, use dummy account
    if (ctx.state.isStaging && authorizationConfig.stagingToken) {
      const token = get(ctx.state, authorizationConfig.stagingToken.position);
      if (isStagingAccount(authorizationConfig, token)) {
        const compiledStagingAccount = deepCompile(
          ctx,
          authorizationConfig.dummyAccount
        );
        setToOutput(ctx, authorizationConfig.output, compiledStagingAccount);

        const primaryId = deepCompile(ctx, authorizationConfig.primaryId);
        ctx.state.sessionId = SessionKey.create(primaryId, ctx.state.env.KEY);

        return next();
      }
    }

    const compiledAuth = deepCompile(ctx, authorizationConfig.input);
    const opts = {
      path: compiledAuth.baseUrl,
      payload: compiledAuth.payload,
      headers: compiledAuth.header,
      raw: true
    };
    const response = await ApiHelper[compiledAuth.method](opts);
    const jsonResponse = response.data;

    if (!isError(response)) {
      setToOutput(ctx, authorizationConfig.output, jsonResponse);
      const primaryId = deepCompile(ctx, authorizationConfig.primaryId);
      ctx.state.sessionId = SessionKey.create(primaryId, ctx.state.env.KEY);

      ctx.state.logger.info(`token-verification`, {
        partnerId: ctx.state.partnerId,
        request: opts,
        response: jsonResponse
      });

      return next();
    } else {
      ctx.state.logger.error(`token-verification-error`, {
        request: opts,
        response: JSON.stringify(jsonResponse)
      });

      throw new WebError(
        UNVERIFIED_ACCOUNT.message,
        UNVERIFIED_ACCOUNT.statusCode,
        UNVERIFIED_ACCOUNT.errorCode,
        UNVERIFIED_ACCOUNT.message,
        false,
        false,
        true
      );
    }
  } catch (err) {
    throw err;
  }
};
