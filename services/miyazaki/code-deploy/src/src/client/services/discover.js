import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';
import { WEBCLIENT } from '../constants/discover';

const { publicRuntimeConfig } = getConfig();
const DISCOVER_URL = publicRuntimeConfig.DISCOVER_BASE_URL;

export const getDiscoverFeed = async (
  region,
  page,
  perPage,
  options = { filterRestrictedContent: false, platform: WEBCLIENT }
) => {
  const { filterRestrictedContent, platform } = options;
  const opts = {
    path: `${DISCOVER_URL}/discover/feed`,
    payload: {
      region,
      page,
      perPage,
      platform,
      filterRestrictedContent
    }
  };

  return JsonHelper.get(opts);
};

export const getPlaylistHistory = async () => {
  const opts = {
    path: `/playlist/history`,
    withCredentials: true
  };

  return JsonHelper.get(opts);
};

export const getRecentPlay = async () => {
  const opts = {
    path: `/playlist/watched`,
    withCredentials: true
  };

  return JsonHelper.get(opts);
};
