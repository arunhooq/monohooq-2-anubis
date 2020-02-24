import React from 'react';
import { connect } from 'react-redux';
import bulmaAccordeon from 'bulma-extensions/bulma-accordion/dist/js/bulma-accordion.min.js';
import 'bulma-extensions/bulma-accordion/dist/css/bulma-accordion.min.css';
import './seasonDetail.scss';
import logger from './../../../common/ClientLogger';
import { setUserSubscription } from './../../actions/userAction';
import IconPlayLight from '../icons/IconPlayLight';
import IconVip from '../icons/IconVip';
import { getEpisodes } from '../../services/detail';
import { prepareGtmPayload } from '../../util/trackingPayload';
import {
  setConsent,
  setEpisode,
  setEpisodes
} from '../../actions/detailAction';
import { get } from 'lodash';
import { setErrorModal } from '../../actions/pagesAction';
import { play } from '../../util/play.js';
import { USER_TYPE } from '../../constants/common';
import { Router } from '../../routes';

const mapStateToProps = state => ({
  discover: state.discover,
  detail: state.detail,
  tracking: state.tracking,
  userSession: state.user.userSession,
  restriction: state.partner.restriction,
  translation: state.translation,
  screen: state.pages.screen
});

const mapDispatchToProps = dispatch => {
  return {
    setEpisodes: episodes => {
      dispatch(setEpisodes(episodes));
    },
    setEpisode: episode => {
      dispatch(setEpisode(episode));
    },
    setConsent: (
      movieId,
      showConsent,
      sku,
      imgHeader,
      partnerLogo,
      cta,
      contentOverride
    ) =>
      dispatch(
        setConsent(
          movieId,
          showConsent,
          sku,
          imgHeader,
          partnerLogo,
          cta,
          contentOverride
        )
      ),
    setUserSubscription: subscription =>
      dispatch(setUserSubscription(subscription)),
    openConsentModal: open => dispatch(openConsentModal(open)),
    setErrorModal: open => dispatch(setErrorModal(open))
  };
};

export class SeasonDetail extends React.Component {
  state = {
    season: null,
    consentOpen: false,
    sku: null,
    paymentUrl: ''
  };

  componentWillMount() {
    const { movie } = this.props.detail;
    const { season } = this.state;
    if (season === null) this.setInitialSeason(movie);
  }

  async componentDidMount() {
    bulmaAccordeon.attach();
  }

  collapseAccordion = () => {
    const activeItem = document.querySelector('.accordion.is-active');
    if (activeItem) {
      activeItem.classList.remove('is-active');
    }
  };

  setInitialSeason = movie => {
    this.setState({ season: movie.seasonList[0] });
  };

  updateEpisodes = async (movie, season) => {
    this.setState({ season });
    const payload = prepareGtmPayload({ movie, opts: { season } });
    this.props.tracking.trackGtm('button_tap_seasons', {
      ...payload,
      source_event_name: `${this.props.page}_page`
    });

    try {
      const episodesData = await getEpisodes(movie.id, season);
      const episodes = episodesData.data !== undefined ? episodesData.data : [];

      this.collapseAccordion();
      this.props.setEpisodes(episodes);
    } catch (error) {
      logger.error(error);
      this.props.setEpisodes([]);
    }
  };

  onConsentOpen = (params = {}, gtmPayload = {}) => {
    const { sku } = params;
    const { imgHeader, partnerLogo, cta, contentOverride } =
      params.consent || {};
    this.props.setConsent(
      this.props.movieId,
      true,
      sku,
      imgHeader,
      partnerLogo,
      cta,
      contentOverride
    );
    this.setState({ sku });
    this.props.tracking.trackGtm('consent_popup_open', {
      ...gtmPayload,
      sku,
      source_event_name: `${this.props.page}_page`
    });
    Router.pushRoute('consent');
  };

  onOpenErrorModal = code => {
    this.props.setErrorModal({ open: true, code });
  };

  play = async episode => {
    const sourceEventName = `${this.props.page}_page`;
    const gtmPayload = prepareGtmPayload({ episode });
    this.props.tracking.trackGtm('button_tap_playepisode', {
      ...gtmPayload,
      source_event_name: sourceEventName
    });

    try {
      this.props.setEpisode(episode);

      const { restriction, userSession, screen } = this.props;
      const heartBeat = get(restriction, 'playback.heartbeat', false);
      const isHeartbeatEnabled = heartBeat && !userSession.isVisitor;

      await play({
        selectedMovie: episode,
        trackGtm: this.props.tracking.trackGtm,
        sourceEventName,
        gtmPayload,
        screen,
        isHeartbeatEnabled,
        openConsentModal: this.onConsentOpen.bind(this)
      });
    } catch (error) {
      logger.error(error);
      this.onOpenErrorModal(error.errorCode);
    }
  };

  handleEpisodeClick(eps) {
    const payload = prepareGtmPayload({ episode: eps });
    this.props.tracking.trackGtm('button_tap_episode', {
      ...payload,
      source_event_name: `${this.props.page}_page`
    });
  }

  renderPlayButton = eps => {
    const { status } = this.props.userSession;
    if (status === USER_TYPE.lapsed) {
      return (
        <IconVip
          style={{ float: 'right' }}
          stroke="#951b81"
          onClick={async () => this.play(eps)}
        />
      );
    }
    return (
      <IconPlayLight
        style={{ float: 'right' }}
        stroke="#951b81"
        onClick={async () => this.play(eps)}
      />
    );
  };

  render() {
    const { season } = this.state;
    const { movie, episodes } = this.props.detail;
    const { seasonList } = movie;

    return (
      <React.Fragment>
        <div className="season_option_wrapper season_option_wrapper--top-border">
          <div className="season_option_container">
            {seasonList.map(seasonItem => {
              return (
                <span
                  key={seasonItem}
                  className={
                    season === seasonItem
                      ? 'season_option_item season_option_item_active'
                      : 'season_option_item'
                  }
                  onClick={() => this.updateEpisodes(movie, seasonItem)}
                >
                  {`Season ${seasonItem}`}
                </span>
              );
            })}
          </div>
        </div>

        <section className="accordions">
          {episodes.map((eps, index) => {
            return (
              <article className="accordion" key={index}>
                <div className="accordion-header">
                  <div
                    className="movie-list-box"
                    onClick={() => this.handleEpisodeClick(eps)}
                  >
                    <div className="episode__title toggle no-tap-highlight">
                      <span className="is-size-6-mobile has-text-left has-text-weight-bold">
                        {index + 1}. {eps.title}
                      </span>
                    </div>

                    <div className="episode__button">
                      {this.renderPlayButton(eps)}
                    </div>
                  </div>
                </div>
                <div className="accordion-body">
                  <div className="accordion-content">
                    <span className="is-small">
                      {eps.running_time_friendly}
                    </span>
                    <br />
                    <span style={{ paddingBottom: '16px' }}>
                      {eps.description}
                    </span>
                    <p>&nbsp;</p>
                  </div>
                </div>
              </article>
            );
          })}
        </section>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SeasonDetail);
