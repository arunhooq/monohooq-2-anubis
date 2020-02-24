const defaultState = {
  searchActive: false,
  keyword: '',
  relatedSearchActive: false,
  relatedSearchResult: {
    title: '',
    movies: []
  },
  relatedTitles: [],
  genres: [],
  crews: [],
  movieResult: [],
  onSearchEvent: false
};

export default function searchReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setSearchActive':
      return { ...state, searchActive: action.payload };
    case 'setKeyword':
      return { ...state, keyword: action.payload };
    case 'setRelatedSearchActive':
      return { ...state, relatedSearchActive: action.payload };
    case 'setRelatedSearchResult':
      return { ...state, relatedSearchResult: action.payload };
    case 'setRelatedTitles':
      return { ...state, relatedTitles: action.payload };
    case 'setGenres':
      return { ...state, genres: action.payload };
    case 'setCrews':
      return { ...state, crews: action.payload };
    case 'setMovieResult':
      return { ...state, movieResult: action.payload };
    case 'setOnSearchEvent':
      return { ...state, onSearchEvent: action.payload };
    default:
      return state;
  }
}
