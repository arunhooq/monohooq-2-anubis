import React, { Component } from 'react';
import { connect } from 'react-redux';
import Curation from '../components/curation/Curation';
import { setMenuTab, setVisibility } from '../actions/menuAction';
import { setLoadingState } from '../actions/loadingAction';
import { setTvShowCurations } from '../actions/curationAction';
import { redirectErrorPage } from '../helpers/redirect';
import { getCurationData } from '../util/curation';
import { TVSHOWS } from '../constants/tabMenu';
import { BROWSE, TVSHOW } from '../constants/movie';
import { setInitialProps, setViewPortPosition } from '../util/pages';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import { CURATIONS_IS_EMPTY } from './../../common/ErrorCodes';
import ClientError from './../../common/ClientError';
import { setScrollPosition } from '../actions/pagesAction';

const mapStateToProps = state => ({
  loading: state.loading,
  discover: state.discover,
  curation: state.curation,
  userSession: state.user.userSession,
  pages: state.pages
});

const mapDispatchToProps = dispatch => ({
  setMenuTab: menu => dispatch(setMenuTab(menu)),
  setLoadingState: isLoading => dispatch(setLoadingState(isLoading)),
  setVisibility: visible => dispatch(setVisibility(visible)),
  setTvShowCurations: curations => dispatch(setTvShowCurations(curations)),
  setScrollPosition: payload => dispatch(setScrollPosition(payload))
});

class TvShows extends Component {
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
    document.title = 'TV Shows';
    this.props.setMenuTab(TVSHOWS);
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
  }

  async setInitialStore() {
    const { asPath } = this.props;
    const { tvshowCurations } = this.props.curation;
    const { region } = this.props.discover;
    let curations = tvshowCurations;

    if (tvshowCurations.length === 0) {
      curations = await getCurationData(BROWSE, TVSHOW, region);
    }

    if (curations.length === 0) {
      return redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'true',
          back: 'false',
          retryUrl: asPath
        },
        new ClientError(
          CURATIONS_IS_EMPTY.errorCode,
          CURATIONS_IS_EMPTY.message
        )
      );
    }

    this.props.setTvShowCurations(curations);
  }

  render() {
    const { userSession, loading } = this.props;
    const { tvshowCurations } = this.props.curation;
    const page = 'tvshows';

    if (loading.isLoading) return null;

    return (
      <React.Fragment>
        <PageLoadTracker trackingTag="tv_shows" userSession={userSession} />
        <div className="section" style={{ padding: '0 1rem' }}>
          <div className="container">
            <Curation curations={tvshowCurations} page={page} />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TvShows);
