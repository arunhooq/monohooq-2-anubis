import React from 'react';
import { connect } from 'react-redux';
import { debounce } from 'lodash';
import { Router } from '../../routes';
import { filterSearchOutAvailability } from '../../helpers/movies';
import { TVOD } from '../../constants/movie';
import {
  setKeyword,
  setMovieResult,
  setSearchActive,
  setCrews,
  setGenres,
  setRelatedTitles,
  setOnSearchEvent
} from '../../actions/searchAction';
import { setSidebar } from '../../actions/menuAction';
import { searchSuggestion } from '../../services/search';
import './searchPanel.scss';

const mapStateToProps = state => ({
  search: state.search,
  menu: state.menu,
  region: state.discover.region,
  tracking: state.tracking
});

const mapDispatchToProps = dispatch => ({
  setKeyword: state => dispatch(setKeyword(state)),
  setMovieResult: state => dispatch(setMovieResult(state)),
  setSearchActive: state => dispatch(setSearchActive(state)),
  setCrews: state => dispatch(setCrews(state)),
  setGenres: state => dispatch(setGenres(state)),
  setRelatedTitles: state => dispatch(setRelatedTitles(state)),
  setOnSearchEvent: state => dispatch(setOnSearchEvent(state)),
  setSidebar: state => dispatch(setSidebar(state))
});

class SearchPanel extends React.Component {
  componentWillUmount() {
    this.debouncedEvent.cancel();
  }

  debounceEvent(...args) {
    this.debouncedEvent = debounce(...args);
    return e => {
      e.persist();
      return this.debouncedEvent(e);
    };
  }

  async handleChange(e) {
    const query = e.target.value;
    this.props.setKeyword(query);

    // prevent request once query is blank
    if (query.length > 0) {
      await this.fetchSuggestion(query);
    } else {
      this.resetSearchData();
    }

    const { router } = Router;
    if (router.asPath !== `/search`) {
      Router.back();
    }
  }

  handleFocus(props) {
    props.tracking.trackGtm('button_tap_search', {
      source_event_name: `search_page`
    });
  }

  async fetchSuggestion(query) {
    let titles = [];
    let crews = [];
    let genres = [];
    let relatedTitles = [];

    try {
      const region = this.props.region;
      this.props.setOnSearchEvent(true);
      const suggestion = await searchSuggestion(query, region);
      this.props.setOnSearchEvent(false);
      if (suggestion.data.titles.data.length > 0) {
        titles = suggestion.data.titles.data;
      }
      if (suggestion.data.crews.data.length > 0) {
        crews = suggestion.data.crews.data;
      }
      if (suggestion.data.tags.data.length > 0) {
        genres = suggestion.data.tags.data.filter(
          tag => tag.meta.hasOwnProperty('genre') && tag.meta.genre === true
        );
      }
      if (suggestion.data.relatedTitles.data.length > 0) {
        relatedTitles = suggestion.data.relatedTitles.data;
      }
    } catch (err) {
      // TODO: should log this
    }

    this.props.setMovieResult(filterSearchOutAvailability(titles, TVOD));
    this.props.setGenres(genres);
    this.props.setCrews(crews);
    this.props.setRelatedTitles(relatedTitles);
  }

  resetSearchData = () => {
    this.props.setKeyword('');
    this.props.setMovieResult([]);
    this.props.setGenres([]);
    this.props.setCrews([]);
    this.props.setRelatedTitles([]);
  };

  setSearchActive = () => {
    this.resetSearchData();
    this.props.setSearchActive(true);

    Router.pushRoute('search');
  };

  setSearchInactive = () => {
    const { router } = Router;
    if (router.asPath) {
      this.props.setSearchActive(false);
    }
    Router.back();
  };

  SearchIconActive = () => {
    return (
      <a
        className="navbar-item navbar-abs"
        onClick={() => this.setSearchActive()}
      >
        <img src="/static/img/search.svg" className="search-icon" />
      </a>
    );
  };

  SearchIconInactive = () => {
    return (
      <a
        className="navbar-item navbar-abs"
        onClick={() => this.setSearchInactive()}
      >
        <img src="/static/img/ic-close.svg" className="search-close" />
      </a>
    );
  };

  SidebarMenu = () => {
    const nextState = !this.props.menu.sidebar;
    return (
      <a
        className="navbar-item navbar-abs"
        onClick={() => this.props.setSidebar(nextState)}
      >
        <img src="/static/img/ic-search-copy.svg" className="hamburger-btn" />
      </a>
    );
  };

  SearchFormInput = () => {
    const { keyword } = this.props.search;
    return (
      <div className="search-input">
        <input
          onFocus={() => this.handleFocus(this.props)}
          onChange={this.debounceEvent(this.handleChange, 500)}
          id="search-input-text"
          className="input has-background-white-ter"
          type="text"
          placeholder="Search movie, TV show or cast.."
          defaultValue={keyword}
          autoComplete="off"
        />
      </div>
    );
  };

  render() {
    const { visible } = this.props.menu;
    const { searchActive } = this.props.search;
    const {
      SearchFormInput,
      SearchIconActive,
      SearchIconInactive,
      SidebarMenu
    } = this;
    return (
      <div>
        {visible && (
          <React.Fragment>
            <nav
              className="navbar is-fixed-top is-mobile"
              role="navigation"
              aria-label="main navigation"
            >
              <div className="navbar-brand">
                <a className="navbar-item" href="/">
                  <img src="/static/img/logo.svg" width="78%" />
                </a>
              </div>
              <div className="navbar-menu is-active">
                <div className="navbar-end">
                  {!searchActive && <SearchIconActive />}
                  {searchActive && <SearchIconInactive />}
                  <SidebarMenu />
                </div>
              </div>
            </nav>
            {searchActive && <SearchFormInput />}
          </React.Fragment>
        )}
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SearchPanel);
