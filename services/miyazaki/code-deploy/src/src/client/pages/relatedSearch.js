import React from 'react';
import { connect } from 'react-redux';
import { setInitialProps } from './../util/pages';
import RelatedMovies from '../components/search/relatedMovies';
import NoResult from '../components/search/noresult';
import HooqLoader from '../components/shared/HooqLoader';
import { setVisibility } from '../actions/menuAction';
import { searchBrowse, searchRelatedMovies } from '../services/search';
import {
  setRelatedSearchResult,
  setSearchActive
} from '../actions/searchAction';
import { CREW_SEARCH, MOVIE_SEARCH, GENRE_SEARCH } from '../constants/search';
import { filterSearchOutAvailability } from '../helpers/movies';
import { TVOD } from '../constants/movie';

const mapStateToProps = state => ({
  search: state.search,
  region: state.discover.region
});

const mapDispatchToProps = dispatch => ({
  setSearchActive: state => dispatch(setSearchActive(state)),
  setRelatedSearchResult: (title, movies) =>
    dispatch(setRelatedSearchResult({ title, movies })),
  setVisibility: state => dispatch(setVisibility(state))
});

class RelatedSearch extends React.Component {
  state = {
    isLoading: false
  };

  static async getInitialProps(ctx) {
    const { query } = ctx;
    await setInitialProps(ctx);
    return { query };
  }

  setRelatedDataMovies(q, label, searchResult) {
    let movies = [];
    if (searchResult.data !== undefined && searchResult.data.length > 0) {
      movies = filterSearchOutAvailability(searchResult.data, TVOD);
    }
    this.props.setRelatedSearchResult(label, movies);
    this.setState({ isLoading: false });
  }

  async componentDidMount() {
    const { type, q, label } = this.props.query;
    const { region } = this.props;
    let searchResult = null;
    this.setState({ isLoading: true });

    this.props.setSearchActive(true);
    this.props.setVisibility(true);

    switch (type) {
      case CREW_SEARCH:
        searchResult = await searchBrowse(q, region, 'crew');
        break;
      case MOVIE_SEARCH:
        searchResult = await searchRelatedMovies(q, region);
        break;
      case GENRE_SEARCH:
        searchResult = await searchBrowse(q, region, 'tag');
      default:
        this.setState({ isLoading: false });
        break;
    }
    this.setRelatedDataMovies(q, label, searchResult);
  }

  componentWillUnmount() {
    this.props.setSearchActive(false);
  }

  hasNoResult() {
    return this.props.search.relatedSearchResult.length === 0;
  }

  render() {
    const { keyword } = this.props.search;
    const { isLoading } = this.state;

    return (
      <React.Fragment>
        <div
          className="container"
          style={{ padding: '0 0.5rem', marginBottom: '1rem' }}
        >
          {!isLoading && <RelatedMovies />}
          {isLoading && <HooqLoader />}
          {this.hasNoResult() && <NoResult keyword={keyword} />}
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RelatedSearch);
