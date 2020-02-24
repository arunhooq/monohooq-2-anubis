export const filterInAvailability = (rowMovies, availability) => {
  return rowMovies.map(row => {
    if (row.data === null) {
      return row;
    }

    const movies = row;
    movies.data = row.data.filter(movie => {
      return movie.availability === availability;
    });

    return movies;
  });
};

export const filterOutAvailability = (rowMovies, availability) => {
  return rowMovies.map(row => {
    if (row.data === null) {
      return row;
    }

    const movies = row;
    movies.data = row.data.filter(movie => {
      return movie.availability !== availability;
    });

    return movies;
  });
};

export const filterSearchOutAvailability = (movies, availability) => {
  return movies.filter(movie => {
    return movie.availability !== availability;
  });
};
