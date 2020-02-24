import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';
import { WEBCLIENT } from '../constants/discover';

const { publicRuntimeConfig } = getConfig();
const DISCOVER_URL = publicRuntimeConfig.DISCOVER_BASE_URL;

export const getCurations = (
  type,
  titleType,
  region,
  page,
  perPage,
  platform = WEBCLIENT
) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/curation`,
    payload: {
      type,
      titleType,
      region,
      page,
      perPage,
      platform
    }
  };

  return JsonHelper.get(opts);
};
