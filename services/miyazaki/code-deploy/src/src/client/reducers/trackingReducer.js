import { SET_TRACKING_STATE } from './../constants/actions';

const defaultState = {};

export default function trackingReducer(state = defaultState, action) {
  switch (action.type) {
    case SET_TRACKING_STATE:
      return { ...state, ...action.payload };
    default:
      return state;
  }
}
