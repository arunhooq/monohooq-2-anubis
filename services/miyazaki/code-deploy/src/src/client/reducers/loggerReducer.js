import { SET_LOGGER_STATE } from './../constants/actions';

const defaultState = {};

export default function loggerReducer(state = defaultState, action) {
  switch (action.type) {
    case SET_LOGGER_STATE:
      return { ...state, ...action.payload };
    default:
      return state;
  }
}
