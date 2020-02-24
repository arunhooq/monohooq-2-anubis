import React from 'react';
import { connect } from 'react-redux';
import { setInitialProps } from './../util/pages';
import {
  setSearchActive,
  setRelatedSearchActive
} from '../actions/searchAction';
import { setVisibility } from '../actions/menuAction';
import MovieResult from '../components/search/movieResult';
import CrewResult from '../components/search/crewResult';
import GenreResult from '../components/search/genreResult';
import RelatedTitles from '../components/search/relatedTitles';
import NoResult from '../components/search/noresult';
import HooqLoader from '../components/shared/HooqLoader';
import NoSSR from 'react-no-ssr';
import { HEIGHT_PANEL_HEADER, COMMON_DEVICE_HEIGHT } from '../constants/common';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';

const mapStateToProps = state => ({
  search: state.search,
  userSession: state.user.userSession,
  tracking: state.tracking
});

const mapDispatchToProps = dispatch => ({
  setSearchActive: state => dispatch(setSearchActive(state)),
  setRelatedSearchActive: state => dispatch(setRelatedSearchActive(state)),
  setVisibility: state => dispatch(setVisibility(state))
});

const SearchResult = props => (
  <div>
    <MovieResult />
    <CrewResult />
    <GenreResult />
    <RelatedTitles />
  </div>
);

class Search extends React.Component {
  state = {
    screen: null
  };

  static async getInitialProps(ctx) {
    const { store } = ctx;
    await setInitialProps(ctx);
    store.dispatch(setSearchActive(true));
    store.dispatch(setVisibility(true));
  }

  hasNoResult() {
    const {
      keyword,
      relatedTitles,
      genres,
      crews,
      movieResult,
      onSearchEvent
    } = this.props.search;
    return (
      keyword !== '' &&
      relatedTitles.length === 0 &&
      genres.length === 0 &&
      crews.length === 0 &&
      movieResult.length === 0 &&
      !onSearchEvent
    );
  }

  componentDidMount() {
    this.setState({ screen: window.screen });
    this.props.setSearchActive(true);
    this.props.setVisibility(true);
  }

  componentWillUnmount() {
    this.props.setSearchActive(false);
  }

  render() {
    const { userSession } = this.props;
    const { relatedSearchActive, keyword, onSearchEvent } = this.props.search;
    const showNoResult = !relatedSearchActive && this.hasNoResult();
    const { screen } = this.state;
    const wrapperHeight = screen
      ? screen.availHeight - HEIGHT_PANEL_HEADER
      : COMMON_DEVICE_HEIGHT;

    return (
      <React.Fragment>
        <PageLoadTracker trackingTag="search" userSession={userSession} />

        {/* initialize search when user not typing the title yet */}
        {!relatedSearchActive &&
          !onSearchEvent &&
          !showNoResult &&
          keyword === '' && (
            <NoSSR>
              <div style={{ minHeight: wrapperHeight }} />
            </NoSSR>
          )}

        <div
          className="container"
          style={{ padding: '0 0.5rem', marginBottom: '1rem' }}
        >
          {!onSearchEvent && <SearchResult />}
          {showNoResult && <NoResult keyword={keyword} />}
        </div>
        {onSearchEvent && <HooqLoader />}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Search);
