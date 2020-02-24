const defaultState = {
  movie: {},
  episode: {},
  episodes: [],
  consent: {
    movieId: '',
    sku: null,
    showConsent: false,
    imgHeader: null,
    partnerLogo: null,
    contentOverride: null,
    cta: []
  },
  isConsentActive: false,
  openConsent: false,
  policy: true,
  term: true,
  config: {},
  relatedMovies: []
};

export default function detailReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setMovie':
      return { ...state, movie: action.payload };
    case 'setConsent':
      return { ...state, consent: action.payload };
    case 'setConsentState':
      return { ...state, isConsentActive: action.payload };
    case 'setConsentPolicy':
      return { ...state, policy: action.payload };
    case 'setConsentTerm':
      return { ...state, term: action.payload };
    case 'setDetailConfig':
      return { ...state, config: action.payload };
    case 'setEpisode':
      return { ...state, episode: action.payload };
    case 'setEpisodes':
      return { ...state, episodes: action.payload };
    case 'setRelatedMovies':
      return { ...state, relatedMovies: action.payload };
    default:
      return state;
  }
}
