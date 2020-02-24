import { SET_TRACKING_STATE } from './../constants/actions';

export const setTrackingState = state => {
  return {
    type: SET_TRACKING_STATE,
    payload: state
  };
};
