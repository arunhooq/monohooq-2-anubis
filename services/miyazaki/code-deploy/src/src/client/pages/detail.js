import React from 'react';
import { connect } from 'react-redux';
import { get, isEmpty } from 'lodash';
import { createPoster } from './../components/shared/Poster';
import HooqSpinner from '../components/shared/HooqSpinner';
import { resizeUrl } from '../components/shared/Poster';
import ContentDetail from '../components/detail/contentDetail';
import SeasonDetail from '../components/detail/seasonDetail';
import MovieDescription from '../components/detail/movieDescription';
import RelatedMovies from '../components/detail/relatedMovies';
import FailedPaymentModal from '../components/detail/failedPaymentModal';
import { setDetailConfig } from '../actions/discoverAction';
import {
  setMovie,
  setEpisodes,
  setRelatedMovies
} from '../actions/detailAction';
import { setLoadingState } from '../actions/loadingAction';
import { setUserSubscription } from '../actions/userAction';
import { setVisibility } from '../actions/menuAction';
import {
  getMovieData,
  getEpisodesData,
  getRelatedMoviesData,
  getDetailConfig,
  getPaymentStatusData
} from '../util/detail';
import { redirectErrorPage } from '../helpers/redirect';
import WatchButton from './../components/detail/watchButton';
import { MOVIE, TVSHOW } from '../constants/movie';
import { setInitialProps } from '../util/pages';
import { prepareGtmPayload } from '../util/trackingPayload';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import { play } from '../util/play';
import { getDetails as getSubscriptionDetails } from './../services/subscription';
import { getMovie } from '../services/detail';
import { Subscription } from './../../common/General';
import { PAYMENT_SUCCESS, PAYMENT_CANCEL } from '../constants/payment';
import TermConditionWrapper from '../components/terms/TermAndConditionWrapper';
import { convertProtocolToHttps } from './../util/common';
import CloseButton from '../components/detail/closeButton';
import { getQueryParams } from '../helpers/url';
import { TITLE_NOT_FOUND } from './../../common/ErrorCodes';
import ClientError from './../../common/ClientError';

const { NEW_USER } = Subscription;

const mapStateToProps = (state, ownProps) => {
  return {
    loading: state.loading,
    discover: state.discover,
    tracking: state.tracking,
    userSession: state.user.userSession,
    translation: state.translation,
    restriction: state.partner.restriction,
    detail: state.detail,
    screen: state.pages.screen
  };
};

const mapDispatchToProps = dispatch => ({
  setLoadingState: isLoading => dispatch(setLoadingState(isLoading)),
  setVisibility: visible => dispatch(setVisibility(visible)),
  setMovie: movie => dispatch(setMovie(movie)),
  setEpisodes: episodes => dispatch(setEpisodes(episodes)),
  setRelatedMovies: relatedMovies => dispatch(setRelatedMovies(relatedMovies)),
  setUserSubscription: subscription =>
    dispatch(setUserSubscription(subscription)),
  setDetailConfig: config => dispatch(setDetailConfig(config))
});

export class Detail extends React.Component {
  static pageTransitionDelayEnter = true;

  state = {
    failedPayment: false,
    failedPaymentUrl: '',
    page: 'detail',
    backToHome: false,
    redirectId: null
  };

  static async getInitialProps(ctx) {
    const { query, asPath } = ctx;
    const { movieId } = query;
    await setInitialProps(ctx);

    return {
      movieId,
      asPath
    };
  }

  componentWillMount() {
    this.props.setVisibility(false);
  }

  async componentDidMount() {
    await this.setInitialStore();
    await this.handlePaymentRedirect();
    await this.updateUserSubscription();

    this.timeoutId = setTimeout(() => {
      this.props.pageTransitionReadyToEnter();
      this.props.setLoadingState(false);
    }, 400);
  }

  componentWillUnmount() {
    if (this.timeoutId) clearTimeout(this.timeoutId);
  }

  async setInitialStore() {
    const { movieId, asPath } = this.props;

    const { redirectId, backToHome } = getQueryParams(location.toString());
    this.setState({
      redirectId,
      backToHome: backToHome === 'true'
    });

    const movieData = await getMovieData(movieId);

    if (Object.keys(movieData).length == 0) {
      return redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'true',
          retryUrl: asPath
        },
        new ClientError(TITLE_NOT_FOUND.errorCode, TITLE_NOT_FOUND.message)
      );
    }

    this.props.setMovie(movieData);

    let episodesData = [];
    if (movieData.as === TVSHOW) {
      const season = movieData.seasonList[0];
      episodesData = await getEpisodesData(movieId, season);
    }
    this.props.setEpisodes(episodesData);

    const relatedMoviesData = await getRelatedMoviesData(movieId, 1, 30);
    this.props.setRelatedMovies(relatedMoviesData.slice(0, 9));

    const config = await getDetailConfig();
    this.props.setDetailConfig(config);
  }

  async handlePaymentRedirect() {
    const { redirectId } = this.state;

    const uri = new URL(location.toString());
    const detailPagePath = uri.href.replace(/\?.*/, ''); // Remove query params from uri

    if (!redirectId) {
      return;
    }

    try {
      const status = await getPaymentStatusData(redirectId);
      const { paymentStatus, titleId, ccPaymentUrl } = status;

      if (titleId === undefined) {
        return;
      }

      const selectedMovie = await getMovie(titleId);
      const gtmPayload =
        get(selectedMovie, 'data.as') === MOVIE
          ? prepareGtmPayload({ movie: selectedMovie.data })
          : prepareGtmPayload({ episode: selectedMovie.data });

      if (paymentStatus === PAYMENT_SUCCESS) {
        const { restriction, userSession, screen } = this.props;

        this.props.tracking.trackGtm(`page_load_payment_success`, gtmPayload);

        const heartBeat = get(restriction, 'playback.heartbeat', false);
        const isHeartbeatEnabled = heartBeat && !userSession.isVisitor;
        const isPaymentRedirect = true;

        await play({
          selectedMovie: selectedMovie.data,
          trackGtm: this.props.tracking.trackGtm,
          sourceEventName: `detail_page`,
          gtmPayload,
          screen,
          isHeartbeatEnabled,
          isPaymentRedirect
        });
      } else if (paymentStatus === PAYMENT_CANCEL) {
        this.props.tracking.trackGtm(`page_load_payment_failed`, gtmPayload);
        this.setState({ failedPaymentUrl: ccPaymentUrl });
        this.toggleFailedPaymentModal();
      }
    } catch (err) {
      redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'true',
          retryUrl: detailPagePath
        },
        err
      );
    }
  }

  async updateUserSubscription() {
    const subscriptionDetails = !this.props.userSession.isVisitor
      ? await getSubscriptionDetails()
      : [];
    this.props.setUserSubscription(subscriptionDetails);
  }

  getWatchButtonText() {
    const { detail, translation, userSession } = this.props;
    const { userLevel } = userSession.user;
    const contentLevel = detail.movie.content_level;

    if (userLevel <= 10 && contentLevel > 10) {
      return translation.contentDetail_watch_subscribe;
    } else if (userLevel <= 20 && contentLevel > 20) {
      return translation.contentDetail_watch_subscribe;
    } else {
      return translation.contentDetail_watch_now;
    }
  }

  toggleFailedPaymentModal = () => {
    this.setState({
      failedPayment: !this.state.failedPayment
    });
  };

  renderWatchButton = page => {
    const { userSession } = this.props;
    const { movie, episodes } = this.props.detail;
    const movieToPlay = movie.as === TVSHOW ? episodes[0] || movie : movie;

    switch (userSession.status) {
      case NEW_USER:
        return (
          <div>
            <TermConditionWrapper>
              <WatchButton
                movie={movieToPlay}
                movieId={movieToPlay.id}
                page={page}
              >
                {this.getWatchButtonText()}
              </WatchButton>
            </TermConditionWrapper>
          </div>
        );
      default:
        return (
          <div>
            <div className="marTop-2rem" />
            <WatchButton
              movie={movieToPlay}
              movieId={movieToPlay.id}
              page={page}
            >
              {this.getWatchButtonText()}
            </WatchButton>
          </div>
        );
    }
  };

  render() {
    const { config, movie } = this.props.detail;
    const { userSession, loading } = this.props;
    const { spinner } = this.props.loading;
    const { backToHome, page } = this.state;

    if (loading.isLoading) {
      return null;
    }

    if (isEmpty(movie)) {
      return null;
    }
    const {
      as: movieAs,
      availability,
      images,
      content_level: contentLevel
    } = movie;

    const poster = images.filter(img => img.type === 'POSTER')[0];
    const adjustedPosterUrl = convertProtocolToHttps(poster.url);
    const resizedPosterUrl = resizeUrl(adjustedPosterUrl, 320, 480);

    const payload =
      movieAs === MOVIE
        ? prepareGtmPayload({ movie })
        : prepareGtmPayload({ episode: movie });

    const Poster = createPoster({
      label: {
        userLevel: userSession.user.userLevel,
        contentLevel,
        availability,
        as: movieAs
      }
    });

    return (
      <React.Fragment>
        <PageLoadTracker
          trackingTag="contentdetails"
          payload={payload}
          userSession={userSession}
        />

        <div className="section" style={{ padding: '0 1.25rem' }}>
          <div className="container">
            <div className="poster-container">
              <div
                className="poster-blur"
                style={{
                  backgroundImage: `url(${resizedPosterUrl})`
                }}
              />
              <div className="poster-wrap">
                <Poster src={resizedPosterUrl} width="320" height="480" />
              </div>
            </div>
            <CloseButton backToHome={backToHome} />
            <div className="has-text-centered">
              {this.renderWatchButton(page)}
            </div>
            <ContentDetail type={movie.as} />
            <MovieDescription />
            {movie.as === TVSHOW && (
              <SeasonDetail movieId={movie.movieId} page={page} />
            )}
            <RelatedMovies titleType={movie.as} page={page} />
          </div>
        </div>
        <FailedPaymentModal
          toggleModal={this.toggleFailedPaymentModal}
          open={this.state.failedPayment}
          paymentUrl={this.state.failedPaymentUrl}
          config={config.failedPaymentModal}
          eventPayload={payload}
        />
        <HooqSpinner show={spinner} />
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Detail);
