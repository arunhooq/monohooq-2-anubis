import { putPlay } from '../../services/catalog';
import logger from '../../../common/ClientLogger';

export const heartbeat = ({ titleId, length, position }) => {
  try {
    putPlay({ titleId, length, position }).then(() => {});
  } catch (err) {
    logger.error(err);
  }
};
