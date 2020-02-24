export const setMovie = movie => ({
  type: 'setMovie',
  payload: movie
});

export const setEpisode = episode => ({
  type: 'setEpisode',
  payload: episode
});

export const setConsent = (
  movieId,
  showConsent,
  sku,
  imgHeader,
  partnerLogo,
  cta,
  contentOverride
) => ({
  type: 'setConsent',
  payload: {
    movieId,
    showConsent,
    sku,
    imgHeader,
    partnerLogo,
    cta,
    contentOverride
  }
});

export const setConsentState = isConsentActive => ({
  type: 'setConsentState',
  payload: { isConsentActive }
});

export const setConsentPolicy = policy => ({
  type: 'setConsentPolicy',
  payload: policy
});

export const setConsentTerm = term => ({
  type: 'setConsentTerm',
  payload: term
});

export const setEpisodes = eps => ({
  type: 'setEpisodes',
  payload: eps
});

export const setRelatedMovies = movies => ({
  type: 'setRelatedMovies',
  payload: movies
});
