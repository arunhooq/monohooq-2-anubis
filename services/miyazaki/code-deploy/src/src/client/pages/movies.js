import React, { Component } from 'react';
import { connect } from 'react-redux';
import Curation from '../components/curation/Curation';
import { setMenuTab, setVisibility } from '../actions/menuAction';
import { setLoadingState } from '../actions/loadingAction';
import { setMovieCurations } from '../actions/curationAction';
import { redirectErrorPage } from '../helpers/redirect';
import { getCurationData } from '../util/curation';
import { MOVIES } from '../constants/tabMenu';
import { BROWSE, MOVIE } from '../constants/movie';
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
  setMovieCurations: curations => dispatch(setMovieCurations(curations)),
  setScrollPosition: payload => dispatch(setScrollPosition(payload))
});

class Movies extends Component {
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
    document.title = 'Movies';
    this.props.setMenuTab(MOVIES);
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
    const { movieCurations } = this.props.curation;
    const { region } = this.props.discover;
    let curations = movieCurations;

    if (movieCurations.length === 0) {
      curations = await getCurationData(BROWSE, MOVIE, region);
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

    this.props.setMovieCurations(curations);
  }

  render() {
    const { userSession, loading } = this.props;
    const { movieCurations } = this.props.curation;
    const page = 'movies';

    if (loading.isLoading) return null;

    return (
      <React.Fragment>
        <PageLoadTracker trackingTag="movies" userSession={userSession} />
        <div className="section" style={{ padding: '0 1rem' }}>
          <div className="container">
            <Curation curations={movieCurations} page={page} />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Movies);
