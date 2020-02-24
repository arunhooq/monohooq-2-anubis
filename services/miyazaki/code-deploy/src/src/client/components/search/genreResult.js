import React from 'react';
import { connect } from 'react-redux';
import TagResult from './tagResult';
import {
  setRelatedSearchActive,
  setRelatedSearchResult
} from '../../actions/searchAction';
import { searchBrowse } from '../../services/search';
import { GENRE_SEARCH } from '../../constants/search';
import { Router } from '../../routes';

const mapStateToProps = state => ({
  search: state.search,
  region: state.discover.region
});

const mapDispatchToProps = dispatch => ({
  setRelatedSearchResult: (title, movies) =>
    dispatch(setRelatedSearchResult({ title, movies })),
  setRelatedSearchActive: state => dispatch(setRelatedSearchActive(state))
});

class GenreResult extends React.Component {
  searchRelated = async genre => {
    Router.pushRoute('relatedSearch', {
      q: genre.id,
      label: genre.label,
      type: GENRE_SEARCH
    });
  };

  render() {
    const { genres, keyword } = this.props.search;
    const show = genres.length > 0;
    return (
      <React.Fragment>
        {show && (
          <div className="container padTop-1rem">
            <span className="search-movie-title">Genre ({genres.length})</span>
            <div className="tags are-medium padTop-05rem">
              {genres.map((genre, index) => (
                <TagResult
                  key={index}
                  label={genre.label}
                  keyword={keyword}
                  onClick={() => this.searchRelated(genre)}
                />
              ))}
            </div>
          </div>
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GenreResult);
