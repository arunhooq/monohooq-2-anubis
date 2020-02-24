const defaultState = {
  isLoading: true,
  spinner: false
};

export default function loadingReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setLoadingState':
      return { ...state, isLoading: action.payload };
    case 'setSpinnerState':
      return { ...state, spinner: action.payload };
    default:
      return state;
  }
}
