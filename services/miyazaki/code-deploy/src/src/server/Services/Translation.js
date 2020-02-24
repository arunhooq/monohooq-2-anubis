const JsonHelper = require('../../common/JsonApiHelper');

const getTranslation = async ctx => {
  const {
    translation: path
  } = ctx.state.partnerConfiguration.CustomConfig.constants;
  return JsonHelper.get({ path });
};

module.exports = {
  getTranslation
};
