import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';

const { publicRuntimeConfig } = getConfig();
const { TRANSLATION_BASE_URL } = publicRuntimeConfig;

export const getTranslation = async (region = 'ID') => {
  let path = `${TRANSLATION_BASE_URL}/translation/${region.toLowerCase()}.json`;
  return JsonHelper.get({ path });
};
