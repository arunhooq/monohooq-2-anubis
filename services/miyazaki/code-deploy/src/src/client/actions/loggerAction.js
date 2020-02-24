import { SET_LOGGER_STATE } from './../constants/actions';

export const setLoggerState = state => {
  return {
    type: SET_LOGGER_STATE,
    payload: state
  };
};
