import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import { get } from 'lodash';
import { play } from '../../util/play';
import { RowContinueWatch } from './rowDisplay';
import { prepareGtmPayload } from '../../util/trackingPayload';
import { MOVIE, TVSHOW } from '../../constants/movie';
import { setErrorModal } from '../../actions/pagesAction';
import { setSpinnerState } from '../../actions/loadingAction';
import { setConsent } from '../../actions/detailAction';
import { setVisibility } from '../../actions/menuAction';
import { CUSTOM_EVENT } from '../../helpers/newrelicUtil';
import logger from './../../../common/ClientLogger';

const mapStateToProps = state => ({
  discover: state.discover,
  tracking: state.tracking,
  translation: state.translation,
  restriction: state.partner.restriction,
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
  setErrorModal: open => dispatch(setErrorModal(open)),
  setSpinnerState: state => dispatch(setSpinnerState(state)),
  setVisibility: state => dispatch(setVisibility(state))
});

class ContinueWatch extends React.Component {
  handleSeeAll = collection => {
    this.props.tracking.trackGtm('button_tap_see_all_collection', {
      obj_id: collection.obj_id,
      row_id: collection.row_id,
      row_name: collection.row_name
    });
    Router.pushRoute('collection', { collectionId: collection.obj_id });
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
    this.props.tracking.trackGtm('consent_popup_open', {
      ...gtmPayload,
      sku,
      source_event_name: `${this.props.page}_page`
    });
    this.props.setVisibility(false);
    Router.pushRoute('consent');
  };

  handlePoster = async movie => {
    const sourceEventName = `${this.props.page}_page`;
    this.props.tracking.trackGtm('button_tap_contentdetails', {
      id: movie.id,
      title: movie.title,
      as: movie.as,
      source_event_name: sourceEventName
    });

    const gtmPayload =
      movie.as === MOVIE
        ? prepareGtmPayload({ movie: movie })
        : prepareGtmPayload({ episode: movie });

    const { restriction, screen } = this.props;
    const isHeartbeatEnabled = get(restriction, 'playback.heartbeat', false);
    const movieToPlay = movie.as === TVSHOW ? movie.lastWatched : movie;

    try {
      await play({
        selectedMovie: movieToPlay,
        trackGtm: this.props.tracking.trackGtm,
        sourceEventName,
        gtmPayload,
        screen,
        isHeartbeatEnabled,
        isPaymentRedirect: false,
        position: movieToPlay.position,
        duration: movieToPlay.duration,
        openConsentModal: this.onOpenModal.bind(this)
      });
    } catch (error) {
      logger.error(error);
      this.onOpenErrorModal(error.errorCode);
    } finally {
      this.props.setSpinnerState(false);
    }
  };

  onOpenErrorModal = code => {
    this.props.setErrorModal({ open: true, code });
  };

  render() {
    const recentWatch = this.props.discover.recentWatch;
    const { translation } = this.props;
    return (
      <React.Fragment>
        {recentWatch.length > 0 && (
          <RowContinueWatch
            rowName={translation.contentDetail_watch_cont}
            isContinueWatch={true}
            movies={recentWatch}
            seeAllClick={this.handleSeeAll}
            posterClick={this.handlePoster}
          />
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ContinueWatch);
