const get = require('lodash.get');
const { getUserPhoneNumber } = require('../Helpers/Config');
const JwtParser = require('../Util/JwtParser');

async function getUserDetail(ctx, session, visitorToken) {
  const opts = { path: '/user' };
  const response = await ctx.state.Api.get(opts);
  let userDetail = get(response, 'data', {});

  const { partnerConfiguration } = ctx.state;
  const { partnerId } = partnerConfiguration.CustomConfig.constants;

  if (visitorToken) {
    return {
      ...userDetail,
      partnerId,
      visitorId: visitorToken.visitorId
    };
  }

  if (!session) {
    session = ctx.state.session.session;
  }

  const primaryId = get(
    partnerConfiguration,
    'CustomConfig.actions.user.primaryId',
    null
  );

  // if phone number
  if (
    primaryId &&
    primaryId.type === 'phoneNumber' &&
    userDetail &&
    !userDetail.phoneNumber
  ) {
    userDetail = getUserPhoneNumber(ctx, userDetail);
  }

  // for future, e.g email
  // ...

  const jwt = JwtParser(session.accessToken);
  return {
    ...userDetail,
    partnerId,
    spAccountId: jwt.sid,
    channelPartnerId: jwt.cp
  };
}

module.exports = {
  getUserDetail
};
