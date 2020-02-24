import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import Poster from '../shared/Poster';
import NoResult from './noresult';
import { setRelatedSearchActive } from '../../actions/searchAction';
import { searchRelatedMovies } from '../../services/search';
import { HEIGHT_PANEL_HEADER } from '../../constants/common';

const mapStateToprops = state => ({
  search: state.search
});

const mapDispatchToProps = dispatch => ({
  setRelatedSearchActive: state => dispatch(setRelatedSearchActive(state))
});

const ColumnPoster = props => {
  const { movie } = props;
  const imageUrl = movie.images
    ? movie.images.filter(img => img.type === 'POSTER')[0].url
    : '';
  return (
    <div
      className="column is-4 is-narrow"
      onClick={() => props.goToDetail(movie.id)}
      style={{ padding: '.18rem' }}
    >
      <Poster src={imageUrl} width="320" height="480" />
    </div>
  );
};

class RelatedMovies extends React.Component {
  searchRelatedTitle = async title => {
    const { region } = this.props;
    const movieResults = await searchRelatedMovies(title.id, region);
    this.props.setRelatedSearchResult(title.title, movieResults.data);
    this.props.setRelatedSearchActive(true);
  };

  goToDetail = movieId => {
    Router.pushRoute('detail', { movieId });
  };

  render() {
    const { title, movies } = this.props.search.relatedSearchResult;
    return (
      <React.Fragment>
        <div
          style={{ minHeight: window.screen.availHeight - HEIGHT_PANEL_HEADER }}
        >
          {movies.length > 0 ? (
            <div className="container padTop-1rem">
              <span className="search-movie-title">
                Result related to "{title}" ({movies.length})
              </span>
              <div className="columns is-mobile is-multiline search-columns">
                {movies.map((el, index) => {
                  return (
                    <ColumnPoster
                      key={index}
                      movie={el}
                      goToDetail={this.goToDetail.bind(this)}
                    />
                  );
                })}
              </div>
            </div>
          ) : (
            <NoResult keyword={title} />
          )}
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToprops,
  mapDispatchToProps
)(RelatedMovies);
