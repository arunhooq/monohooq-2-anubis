import logger from '../../common/ClientLogger';
import { getCurations } from '../services/curation';
import { filterOutAvailability } from '../helpers/movies';
import { TVOD } from '../constants/movie';

export const getCurationData = async (
  type,
  titleType,
  region,
  page = 1,
  perPage = 25
) => {
  try {
    const curations = await getCurations(
      type,
      titleType,
      region,
      page,
      perPage
    );
    const movieCurations =
      curations.data !== undefined
        ? filterOutAvailability(curations.data, TVOD)
        : [];
    return movieCurations;
  } catch (error) {
    logger.error(error);
    return [];
  }
};
