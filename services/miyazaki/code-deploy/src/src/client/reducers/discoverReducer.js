const defaultState = {
  movieGallery: [],
  banners: [],
  liveTv: [],
  collection: {
    name: '',
    titles: []
  },
  quicklinks: [],
  recentWatch: [],
  region: '',
  R21ratings: [],
  discoverRows: []
};

export default function discoverReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setRegion':
      return { ...state, region: action.payload };
    case 'setMovieGallery':
      return { ...state, movieGallery: action.payload };
    case 'setTitlesCollection':
      return { ...state, collection: action.payload };
    case 'setBanners':
      return { ...state, banners: action.payload };
    case 'setLiveTv':
      return { ...state, liveTv: action.payload };
    case 'setQuicklinks':
      return { ...state, quicklinks: action.payload };
    case 'setRecentWatch':
      return { ...state, recentWatch: action.payload };
    case 'setDiscoverRows':
      return { ...state, discoverRows: action.payload };
    default:
      return state;
  }
}
