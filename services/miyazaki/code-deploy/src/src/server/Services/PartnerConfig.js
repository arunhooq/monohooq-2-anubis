const axios = require('axios');

const WebError = require('../Errors/WebError');
const JsonApiHelper = require('../../common/JsonApiHelper');
const redis = require('../../connections/redis');
const { FAILED_GETTING_PARTNER_CONFIG } = require('../../common/ErrorCodes');

async function getPartnerConfig(ctx, partnerId) {
  try {
    const key = `${ctx.state.redisKey}:config:${partnerId}`;
    const partnerConfig = await redis.getAsync(key);

    if (partnerConfig) {
      return JSON.parse(partnerConfig);
    }

    const response = await JsonApiHelper.get({
      path: `${ctx.state.env.KASHMIR_BASE_URL}/config`,
      payload: {
        partner_id: partnerId,
        partner_fields:
          'ID,PartnerId,ChannelPartnerID,IsActive,HMACKeys,CustomConfig',
        app_fields: 'ID,PartnerId,ApplicationName,ApplicationSecret,IsActive'
      }
    });

    if (ctx.state.env.KASHMIR_ENABLE_CACHE === 'true') {
      await redis.setAsync(key, JSON.stringify(response.data), 'EX', 600);
    }

    return response.data;
  } catch (err) {
    throw new WebError(
      err.message,
      FAILED_GETTING_PARTNER_CONFIG.statusCode,
      FAILED_GETTING_PARTNER_CONFIG.errorCode,
      FAILED_GETTING_PARTNER_CONFIG.message,
      false,
      false,
      true
    );
  }
}

async function getPartnerConfigHealthCheck(ctx, partnerId) {
  try {
    const response = await axios.get(
      `${
        ctx.state.env.KASHMIR_BASE_URL
      }/config?partner_id=grab&partner_fields=ID%2CPartnerId%2CChannelPartnerID%2CIsActive%2CHMACKeys%2CCustomConfig&app_fields=ID%2CPartnerId%2CApplicationName%2CApplicationSecret%2CIsActive`
    );

    return response.data;
  } catch (err) {
    throw err;
  }
}

module.exports = {
  getPartnerConfig,
  getPartnerConfigHealthCheck
};
