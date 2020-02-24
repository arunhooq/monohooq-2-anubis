import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import { createPoster } from './../shared/Poster';

const mapStateToProps = state => ({
  search: state.search,
  userSession: state.user.userSession
});

const ColumnPoster = props => {
  const { movie, subscription, userLevel } = props;
  const { content_level: contentLevel, availability } = movie;
  const src = movie.images.length
    ? movie.images.filter(image => image.type === 'POSTER')[0].url
    : '';

  const handleClickPosterClick = movie => {
    Router.pushRoute('detail', {
      movieId: movie.id
    });
  };

  const Poster = createPoster({
    label: {
      userLevel,
      contentLevel,
      availability,
      as: movie.as
    }
  });

  return (
    <div
      onClick={() => handleClickPosterClick(movie)}
      className="column is-4 is-narrow"
      style={{ padding: '.18rem' }}
    >
      <Poster src={src} width="320" height="480" />
    </div>
  );
};

class MovieResult extends React.Component {
  render() {
    const { userSession } = this.props;
    const { movieResult } = this.props.search;
    const show = movieResult.length > 0;
    return (
      <React.Fragment>
        {show && (
          <div className="container padTop-1rem">
            <span className="search-movie-title">
              Movies & TV ({movieResult.length})
            </span>
            <div className="columns is-mobile is-multiline search-columns">
              {movieResult.map((movie, index) => {
                return (
                  <ColumnPoster
                    key={index}
                    movie={movie}
                    subscription={userSession.subscription}
                    userLevel={userSession.user.userLevel}
                  />
                );
              })}
            </div>
          </div>
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(MovieResult);
