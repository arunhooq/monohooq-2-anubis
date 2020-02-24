import { get } from 'lodash';

const parseMovie = movie => {
  const payload = {
    id: movie.id,
    title: movie.title,
    as: movie.as,
    availability: movie.availability,
    ageRating: get('movie.meta.ageRating', null)
  };
  return payload;
};

const parseEpisode = episode => {
  const payload = {
    id: episode.id,
    title: episode.title,
    as: episode.as,
    availability: episode.availability,
    ageRating: episode.meta ? episode.meta.ageRating : 0,
    parent_id: episode.parent_id,
    season: episode.season,
    seasons: episode.seasons,
    episode: episode.episode
  };
  return payload;
};

const parseByMovieAs = (movie, episode) => {
  let payload = {};
  if (movie.as === 'TVSHOW') {
    payload = parseEpisode(episode);
  } else {
    payload = parseMovie(movie);
  }
  return payload;
};

export const prepareGtmPayload = ({ movie, episode, opts = {} }) => {
  let payload = {};

  if (movie !== undefined && episode !== undefined) {
    payload = parseByMovieAs(movie, episode);
  } else if (movie !== undefined) {
    payload = parseMovie(movie);
  } else {
    payload = parseEpisode(episode);
  }

  payload = { ...payload, ...opts };
  return payload;
};
