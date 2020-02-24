import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';
import { WEBCLIENT } from '../constants/discover';

const { publicRuntimeConfig } = getConfig();
const DISCOVER_URL = publicRuntimeConfig.DISCOVER_BASE_URL;

export const getMovie = (movieId, platform = WEBCLIENT) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/titles/${movieId}`,
    payload: {
      platform
    }
  };

  return JsonHelper.get(opts);
};

export const getEpisodes = (movieId, season) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/titles/${movieId}/episodes`,
    payload: { season }
  };

  return JsonHelper.get(opts);
};

export const getRelatedMovies = (movieId, page, perPage) => {
  const opts = {
    path: `${DISCOVER_URL}/discover/titles/${movieId}/related`,
    payload: {
      page,
      perPage
    }
  };

  return JsonHelper.get(opts);
};

export const getPaymentStatus = async redirectId => {
  let path = `/payment/${redirectId}`;

  return JsonHelper.get({
    path,
    withCredentials: true
  });
};

export const getConfigOfDetail = async () => {
  let path = `/config/detail`;

  return JsonHelper.post({
    path,
    withCredentials: true
  });
};
