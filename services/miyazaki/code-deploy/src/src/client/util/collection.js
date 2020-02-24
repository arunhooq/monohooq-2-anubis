import logger from '../../common/ClientLogger';
import { getCollectionTitles, getCollection } from '../services/collection';
import { TVOD } from '../constants/movie';

export const getCollectionTitlesData = async (
  collectionId,
  page = 1,
  perPage = 25
) => {
  try {
    const titles = await getCollectionTitles(collectionId, page, perPage);
    return titles.data !== undefined
      ? titles.data.filter(mv => mv.availability !== TVOD)
      : [];
  } catch (error) {
    logger.error(error);
    return [];
  }
};

export const getCollectionData = async collectionId => {
  try {
    const collection = await getCollection(collectionId);
    return collection.data !== undefined ? collection.data.name : '';
  } catch (error) {
    logger.error(error);
    return '';
  }
};
