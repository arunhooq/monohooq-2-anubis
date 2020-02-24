import React from 'react';
import { connect } from 'react-redux';
import get from 'lodash.get';
import { Router } from '../routes';
import Category from '../components/discover/categories';
import Channels from '../components/discover/channels';
import MovieGallery from '../components/discover/movieGallery';
import ContinueWatch from '../components/discover/continueWatch';
import { setRecentWatch, setDiscoverRows } from '../actions/discoverAction';
import { setLoadingState } from '../actions/loadingAction';
import { setMenuTab, setVisibility } from '../actions/menuAction';
import { setToastNotification } from '../actions/pagesAction';
import { redirectErrorPage } from '../helpers/redirect';
import { DISCOVER } from '../constants/tabMenu';
import { setInitialProps, setViewPortPosition } from '../util/pages';
import { getMoviesFeed, getRecentPlayFeed, getHistory } from '../util/discover';
import { getRewardsDetail } from '../helpers/rewards';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import CarouselDisplay from '../components/discover/carouselDisplay';
import { TWO_SECONDS_DELAY, FIVE_SECONDS_DELAY } from '../constants/common';
import {
  CONTINUE_WATCHING,
  LIVE_TV,
  MULTI_TITLE_LAPSED_USER,
  MULTI_TITLE_MANUAL_CURATION_FREE,
  MULTI_TITLE_MANUAL_CURATION,
  MULTI_TITLE_POPULAR,
  MULTI_TITLE_SPOTLIGHT_FREE,
  MULTI_TITLE_SPOTLIGHT,
  QUICKLINKS,
  SPOTLIGHT
} from '../constants/movie';
import { getQueryParams } from '../helpers/url';
import { DISCOVER_FEEDS_IS_EMPTY } from './../../common/ErrorCodes';
import ClientError from './../../common/ClientError';
import { setScrollPosition } from '../actions/pagesAction';

const mapStateToProps = state => {
  return {
    loading: state.loading,
    discover: state.discover,
    logger: state.logger,
    channel: state.channel,
    userSession: state.user.userSession,
    restriction: state.partner.restriction,
    tracking: state.tracking,
    partnerConfig: state.partner,
    tracking: state.tracking,
    translation: state.translation,
    pages: state.pages
  };
};

const mapDispatchToProps = dispatch => ({
  setMenuTab: menu => dispatch(setMenuTab(menu)),
  setLoadingState: isLoading => dispatch(setLoadingState(isLoading)),
  setVisibility: visible => dispatch(setVisibility(visible)),
  setMovieGallery: gallery => dispatch(setMovieGallery(gallery)),
  setBanners: banners => dispatch(setBanners(banners)),
  setLiveTv: tvs => dispatch(setLiveTv(tvs)),
  setQuicklinks: links => dispatch(setQuicklinks(links)),
  setRecentWatch: movies => dispatch(setRecentWatch(movies)),
  setDiscoverRows: discoverRows => dispatch(setDiscoverRows(discoverRows)),
  setToastNotification: open => dispatch(setToastNotification(open)),
  setScrollPosition: payload => dispatch(setScrollPosition(payload))
});

export class Discover extends React.Component {
  static pageTransitionDelayEnter = true;

  static async getInitialProps(ctx) {
    const { asPath, pathname } = ctx;
    await setInitialProps(ctx);
    return { asPath, pathname };
  }

  componentWillMount() {
    this.props.setVisibility(true);
  }

  async componentDidMount() {
    document.title = 'Discover';
    this.props.setMenuTab(DISCOVER);
    await this.setInitialStore();

    this.timeoutId = setTimeout(() => {
      this.props.pageTransitionReadyToEnter();
      this.props.setLoadingState(false);

      const { loading, pages, pathname } = this.props;
      setViewPortPosition(loading.isLoading, pages, pathname);
    }, 400);
  }

  componentWillUnmount() {
    if (this.timeoutId) clearTimeout(this.timeoutId);
    this.closeRewardsNotification();
  }

  async setInitialStore() {
    const { asPath, restriction, userSession } = this.props;
    const { region, discoverRows } = this.props.discover;
    const { activatedWith, showLinkTV } = getQueryParams(location.toString());
    let moviesRows = discoverRows;

    if (discoverRows.length === 0) {
      moviesRows = await getMoviesFeed(region, 1, 50, {
        filterRestrictedContent: restriction.r21.enable
      });
    }

    const isContinueWatchingEnabled = get(
      restriction,
      'playback.continueWatching',
      false
    );

    if (isContinueWatchingEnabled && !userSession.isVisitor) {
      // we call history playlist first on purpose before fetch the watched playlist
      await getHistory();
      const recentMovies = await getRecentPlayFeed();
      this.props.setRecentWatch(recentMovies);
    }

    if (moviesRows.length === 0) {
      return redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'false',
          retryUrl: asPath
        },
        new ClientError(
          DISCOVER_FEEDS_IS_EMPTY.errorCode,
          DISCOVER_FEEDS_IS_EMPTY.message
        )
      );
    }

    this.props.setDiscoverRows(moviesRows);

    if (activatedWith) {
      this.timeoutId = setTimeout(
        () => this.showRewardsNotification(activatedWith),
        TWO_SECONDS_DELAY
      );
    }
  }

  handlePosterClick = movie => {
    this.props.tracking.trackGtm('button_tap_contentdetails', {
      id: movie.id,
      title: movie.title,
      as: movie.as,
      source_event_name: `discover_page`
    });

    Router.pushRoute('detail', {
      movieId: movie.id
    });
  };

  showRewardsNotification = activatedWith => {
    const { partnerConfig, translation, userSession } = this.props;
    const { title, subtitle } = getRewardsDetail({
      partnerConfig,
      translation,
      activatedWith,
      subscription: userSession.subscription
    });

    if (title && subtitle) {
      this.props.setToastNotification({ open: true, title, subtitle });
      clearTimeout(this.timeoutId);

      this.props.tracking.trackGtm('rewards_activation_success', {
        sku: activatedWith,
        source_event_name: `discover_page`
      });

      this.timeoutId = setTimeout(
        () => this.closeRewardsNotification(),
        FIVE_SECONDS_DELAY
      );
    }
  };

  closeRewardsNotification = () => {
    this.props.setToastNotification({ open: false, title: '', subtitle: '' });
    clearTimeout(this.timeoutId);
  };

  renderRows(page) {
    const { userSession, translation } = this.props;
    const { discoverRows } = this.props.discover;

    return discoverRows.map((row, index) => {
      // NOTE: https://hooqtv.atlassian.net/wiki/spaces/DIS/pages/101024514/Discover+Feed+V2#DiscoverFeedV2-RowTypeDefinition
      switch (row.type) {
        case MULTI_TITLE_SPOTLIGHT:
        case MULTI_TITLE_SPOTLIGHT_FREE:
          return (
            <CarouselDisplay
              key={index}
              row={row}
              onClick={this.handlePosterClick}
              subscription={userSession.subscription}
              userLevel={userSession.user.userLevel}
              imageType={SPOTLIGHT}
              translation={translation}
            />
          );
        case QUICKLINKS:
          return <Category key={index} row={row} />;
        case LIVE_TV:
          return <Channels page={page} key={index} row={row} />;
        case CONTINUE_WATCHING:
          return <ContinueWatch page={page} key={index} />;
        case MULTI_TITLE_MANUAL_CURATION:
        case MULTI_TITLE_MANUAL_CURATION_FREE:
        case MULTI_TITLE_POPULAR:
        case MULTI_TITLE_LAPSED_USER:
        case MULTI_TITLE_POPULAR:
          return <MovieGallery page={page} key={index} row={row} />;
      }
    });
  }

  render() {
    const { loading, userSession } = this.props;
    const page = 'discover';

    if (loading.isLoading) return null;

    return (
      <React.Fragment>
        <PageLoadTracker trackingTag="discover" userSession={userSession} />
        <div className="section" style={{ padding: '0 1.25rem' }}>
          <div className="container">{this.renderRows(page)}</div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Discover);
