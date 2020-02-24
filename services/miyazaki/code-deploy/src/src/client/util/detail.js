import logger from '../../common/ClientLogger';
import {
  getMovie,
  getEpisodes,
  getRelatedMovies,
  getPaymentStatus,
  getConfigOfDetail
} from '../services/detail';
import { TVOD } from '../constants/movie';

export const getMovieData = async movieId => {
  try {
    const movie = await getMovie(movieId);
    return movie.data !== undefined ? movie.data : {};
  } catch (error) {
    logger.error(error);
    return {};
  }
};

export const getEpisodesData = async (movieId, season) => {
  try {
    const episodes = await getEpisodes(movieId, season);
    return episodes.data !== undefined ? episodes.data : [];
  } catch (error) {
    logger.error(error);
    return [];
  }
};

export const getRelatedMoviesData = async (movieId, page = 1, perPage = 25) => {
  try {
    const relatedMovies = await getRelatedMovies(movieId, page, perPage);
    const relatedMoviesData =
      relatedMovies.data !== undefined
        ? relatedMovies.data.filter(mv => mv.availability !== TVOD)
        : [];
    return relatedMoviesData;
  } catch (error) {
    logger.error(error);
    return [];
  }
};

export const getPaymentStatusData = async redirectId => {
  try {
    const status = await getPaymentStatus(redirectId);
    return status.data !== undefined ? status.data : {};
  } catch (error) {
    logger.error(error);
    return {};
  }
};

export const getDetailConfig = async () => {
  try {
    const config = await getConfigOfDetail();
    return config.data !== undefined ? config.data : {};
  } catch (error) {
    logger.error(error);
    return {};
  }
};
