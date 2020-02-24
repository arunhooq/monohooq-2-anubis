import logger from '../.../../../common/ClientLogger';
import {
  getDiscoverFeed,
  getPlaylistHistory,
  getRecentPlay
} from '../services/discover';
import { filterOutAvailability } from '../helpers/movies';
import { TVOD } from '../constants/movie';
import { WEBCLIENT } from '../constants/discover';

export const getMoviesFeed = async (
  region,
  page = 1,
  perPage = 50,
  { filterRestrictedContent }
) => {
  try {
    const movies = await getDiscoverFeed(region, page, perPage, {
      filterRestrictedContent,
      platform: WEBCLIENT
    });
    const rowMovies =
      movies.data !== undefined ? filterOutAvailability(movies.data, TVOD) : [];
    return rowMovies;
  } catch (error) {
    logger.error(error);
    return [];
  }
};

export const getHistory = async () => {
  try {
    await getPlaylistHistory();
  } catch (error) {
    logger.error(error);
  }
};

export const getRecentPlayFeed = async () => {
  try {
    const movies = await getRecentPlay();
    return movies.data;
  } catch (error) {
    logger.error(error);
    return [];
  }
};
