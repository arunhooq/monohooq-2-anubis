import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';
import { WEBCLIENT } from '../constants/discover';

const { publicRuntimeConfig } = getConfig();

const DISCOVER_URL = publicRuntimeConfig.DISCOVER_BASE_URL;

export const getCollectionTitles = (
  collectionId,
  page,
  perPage,
  platform = WEBCLIENT
) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/collections/${collectionId}/titles`,
    payload: {
      page,
      perPage,
      platform
    }
  };

  return JsonHelper.get(opts);
};

export const getCollection = (collectionId, platform = WEBCLIENT) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/collections/${collectionId}`,
    payload: {
      platform
    }
  };

  return JsonHelper.get(opts);
};
