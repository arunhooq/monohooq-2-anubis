import React from 'react';
import { Button } from 'react-bulma-components';
import { connect } from 'react-redux';
import { setConsent } from '../../actions/detailAction';
import { setSpinnerState } from '../../actions/loadingAction';
import { setErrorModal } from '../../actions/pagesAction';
import logger from './../../../common/ClientLogger';
import { CUSTOM_EVENT } from '../../helpers/newrelicUtil';
import { prepareGtmPayload } from '../../util/trackingPayload';
import { get } from 'lodash';
import { play } from '../../util/play';
import './watchButton.scss';
import { Router } from '../../routes';

const mapStateToProps = state => ({
  consent: state.detail.consent,
  detail: state.detail,
  discover: state.discover,
  isConsentActive: state.detail.isConsentActive,
  openConsent: state.detail.openConsent,
  restriction: state.partner.restriction,
  tracking: state.tracking,
  userSession: state.user.userSession,
  screen: state.pages.screen
});

const mapDispatchToProps = dispatch => ({
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
  openAlertModal: open => dispatch(openAlertModal(open)),
  openConsentModal: open => dispatch(openConsentModal(open)),
  setErrorModal: open => dispatch(setErrorModal(open)),
  setSpinnerState: state => dispatch(setSpinnerState(state))
});

class WatchButton extends React.Component {
  state = {
    sku: null
  };

  onOpenModal = (params = {}, gtmPayload = {}) => {
    logger.trace({
      event: CUSTOM_EVENT.OPEN_POPUP_CONSENT.TAG,
      type: CUSTOM_EVENT.OPEN_POPUP_CONSENT.TYPE.OPEN
    });
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
    this.setState({ sku: params.sku });
    this.props.tracking.trackGtm('consent_popup_open', {
      ...gtmPayload,
      sku,
      source_event_name: `${this.props.page}_page`
    });
    Router.pushRoute('consent');
  };

  onCloseModal = () => {
    logger.trace({
      event: CUSTOM_EVENT.OPEN_POPUP_CONSENT.TAG,
      type: CUSTOM_EVENT.OPEN_POPUP_CONSENT.TYPE.DECLINE
    });
    if (!this.props.isConsentActive) {
      this.props.tracking.trackGtm('consent_popup_decline', {
        source_event_name: `${this.props.page}_page`
      });
    }
  };

  onOpenErrorModal = code => {
    this.props.setErrorModal({ open: true, code });
  };

  play = async movie => {
    this.props.setSpinnerState(true);

    const sourceEventName = `${this.props.page}_page`;
    const gtmPayload = prepareGtmPayload({ movie });
    this.props.tracking.trackGtm('button_tap_play', {
      ...gtmPayload,
      source_event_name: sourceEventName
    });

    try {
      const { restriction, userSession, screen } = this.props;
      const heartBeat = get(restriction, 'playback.heartbeat', false);
      const isHeartbeatEnabled = heartBeat && !userSession.isVisitor;

      await play({
        selectedMovie: movie,
        trackGtm: this.props.tracking.trackGtm,
        sourceEventName,
        gtmPayload,
        screen,
        isHeartbeatEnabled,
        openConsentModal: this.onOpenModal.bind(this)
      });
    } catch (error) {
      logger.error(error);
      this.onOpenErrorModal(error.errorCode);
    } finally {
      this.props.setSpinnerState(false);
    }
  };

  handlePlay = async () => {
    const { movie } = this.props;
    this.play(movie);
  };

  render() {
    const { children } = this.props;

    return (
      <React.Fragment>
        <Button
          className="is-large is-fullwidth watch-button has-text-white has-background-primary"
          onClick={this.handlePlay}
        >
          {children}
        </Button>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WatchButton);
