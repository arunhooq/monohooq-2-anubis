import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';

const { publicRuntimeConfig } = getConfig();
const SEARCH_URL = publicRuntimeConfig.SEARCH_BASE_URL;

export const searchBrowse = async (
  query,
  region,
  type,
  page = 1,
  perPage = 25,
  availability = 'SVOD'
) => {
  const path = `${SEARCH_URL}/2.0/search/browse`;
  const headers = { 'api-version': 1.1 };
  const payload = {
    region,
    type,
    page,
    perPage,
    availability,
    query
  };
  return JsonHelper.get({ path, headers, payload });
};

export const searchSuggestion = async (
  query,
  region,
  page = 1,
  perPage = 25,
  availability = 'SVOD'
) => {
  const path = `${SEARCH_URL}/2.0/search/suggestion`;
  const headers = { 'api-version': 1.1 };
  const payload = {
    region,
    page,
    perPage,
    availability,
    query
  };
  return JsonHelper.get({ path, headers, payload });
};

export const searchRelatedMovies = async (relatedtitlesId, region) => {
  const path = `${SEARCH_URL}/2.0/search/relatedtitles`;
  const headers = { 'api-version': 1.1 };
  const payload = {
    id: relatedtitlesId,
    region
  };
  return JsonHelper.get({ path, headers, payload });
};
