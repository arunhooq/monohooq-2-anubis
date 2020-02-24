export const setSearchActive = state => ({
  type: 'setSearchActive',
  payload: state
});

export const setKeyword = word => ({
  type: 'setKeyword',
  payload: word
});

export const setRelatedSearchResult = payload => ({
  type: 'setRelatedSearchResult',
  payload: payload
});

export const setRelatedSearchActive = state => ({
  type: 'setRelatedSearchActive',
  payload: state
});

export const setRelatedTitles = state => ({
  type: 'setRelatedTitles',
  payload: state
});

export const setMovieResult = movies => ({
  type: 'setMovieResult',
  payload: movies
});

export const setGenres = genres => ({
  type: 'setGenres',
  payload: genres
});

export const setCrews = crews => ({
  type: 'setCrews',
  payload: crews
});

export const setOnSearchEvent = event => ({
  type: 'setOnSearchEvent',
  payload: event
});
