const defaultState = {
  movieCurations: [],
  tvshowCurations: []
};

export default function curationReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setMovieCurations':
      return { ...state, movieCurations: action.payload };
    case 'setTvShowCurations':
      return { ...state, tvshowCurations: action.payload };
    default:
      return state;
  }
}
