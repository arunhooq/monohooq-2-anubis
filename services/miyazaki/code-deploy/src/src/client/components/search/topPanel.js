import React from 'react';
import { connect } from 'react-redux';
import debounce from 'lodash.debounce';
import get from 'lodash.get';
import { withCookies } from 'react-cookie';
import dayjs from 'dayjs';
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
import { setSidebar, setCast } from '../../actions/menuAction';
import { searchSuggestion } from '../../services/search';
import { UNIX_EPOCH_TIME, MINUTE } from '../../constants/common';
import IconCast from '../icons/IconCast';
import IconSearch from '../icons/IconSearch';
import IconClose from '../icons/IconClose';
import IconSidebar from '../icons/IconSidebar';
import { getQueryParams } from '../../helpers/url';
import './topPanel.scss';

const mapStateToProps = state => ({
  search: state.search,
  menu: state.menu,
  region: state.discover.region,
  tracking: state.tracking,
  userSession: state.user.userSession,
  partner: state.partner
});

const mapDispatchToProps = dispatch => ({
  setKeyword: state => dispatch(setKeyword(state)),
  setMovieResult: state => dispatch(setMovieResult(state)),
  setSearchActive: state => dispatch(setSearchActive(state)),
  setCrews: state => dispatch(setCrews(state)),
  setGenres: state => dispatch(setGenres(state)),
  setRelatedTitles: state => dispatch(setRelatedTitles(state)),
  setOnSearchEvent: state => dispatch(setOnSearchEvent(state)),
  setSidebar: state => dispatch(setSidebar(state)),
  setCast: state => dispatch(setCast(state))
});

class TopPanel extends React.Component {
  componentDidMount() {
    const { cookies } = this.props;
    const linkTVKey = 'showLinkTV';
    const params = getQueryParams(location.toString());

    const shouldRemoveLinkTV = get(params, 'showLinkTV', '') === 'false';
    if (shouldRemoveLinkTV) {
      this.removeLinkTVIndicator(linkTVKey);
      return;
    }

    const showLinkTV = cookies.get(linkTVKey);
    if (showLinkTV === 'true') {
      this.props.setCast(true);
      this.removeLinkTVIndicator(linkTVKey);
      return;
    }
  }

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

  removeLinkTVIndicator(linkTVKey) {
    const { cookies } = this.props;
    cookies.remove(linkTVKey, {
      path: '/',
      expires: dayjs(UNIX_EPOCH_TIME).toDate()
    });
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

  onSidebarBtnClick = () => {
    const nextState = !this.props.menu.sidebar;
    this.props.setSidebar(nextState);
  };

  PanelMenuItem = props => {
    const { onclick, classname } = props;
    return (
      <a
        className={`navbar-item navbar-abs ${classname || ''}`}
        onClick={() => onclick()}
      >
        {props.children}
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

  onCastButtonClick = () => {
    const { cookies, partner, userSession } = this.props;
    if (
      this.isEmptyPrimaryID() &&
      this.isValidChannelPartnerID() &&
      !userSession.isEvWebviewSignin
    ) {
      const linkTVKey = 'showLinkTV';
      cookies.set(linkTVKey, true, {
        path: '/',
        expires: dayjs(new Date())
          .add(1, MINUTE)
          .toDate()
      });

      const { hobbitBaseUrl } = partner.constants;
      window.location.href = hobbitBaseUrl;
      return;
    }

    this.props.setCast(true);
  };

  isEmptyPrimaryID() {
    const { userSession, partner } = this.props;
    const primaryIdType = get(partner, 'actions.user.primaryId.type', '');
    const primaryId = get(userSession, `user.${primaryIdType}`, '');
    return primaryId === '';
  }

  isValidChannelPartnerID() {
    const { userSession, partner } = this.props;
    return userSession.channelPartnerId === partner.constants.channelPartnerId;
  }

  render() {
    const { partner, userSession } = this.props;
    const { linkTv } = partner.restriction;

    const { visible } = this.props.menu;
    const { searchActive } = this.props.search;
    const { SearchFormInput, PanelMenuItem } = this;
    return (
      <div className="top-panel-wrapper">
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
                  {userSession && !userSession.isVisitor && linkTv.enable && (
                    <PanelMenuItem onclick={this.onCastButtonClick}>
                      <IconCast className="menu-btn" />
                    </PanelMenuItem>
                  )}

                  {!searchActive && (
                    <PanelMenuItem
                      onclick={this.setSearchActive}
                      classname="search-pane"
                    >
                      <IconSearch className="search-icon" />
                    </PanelMenuItem>
                  )}
                  {searchActive && (
                    <PanelMenuItem
                      onclick={this.setSearchInactive}
                      classname="search-pane"
                    >
                      <IconClose className="search-close" />
                    </PanelMenuItem>
                  )}
                  <PanelMenuItem onclick={this.onSidebarBtnClick}>
                    <IconSidebar className="menu-btn" />
                  </PanelMenuItem>
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

const ConnectedTopPanel = connect(
  mapStateToProps,
  mapDispatchToProps
)(TopPanel);
export { ConnectedTopPanel };

export default withCookies(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(TopPanel)
);
