import { Router } from '../routes';
import Consent from '../helpers/consent';
import { redirectErrorPage } from '../helpers/redirect';
import { INELIGIBLE_ACCESS } from '../../common/ErrorCodes';
import ClientError from './../../common/ClientError';

export const play = async ({
  selectedMovie,
  trackGtm,
  sourceEventName,
  gtmPayload,
  screen,
  isHeartbeatEnabled = false,
  isPaymentRedirect = false,
  openConsentModal,
  position = 0,
  duration = 0
}) => {
  const detailPageUrl = window.location.href.replace(/\/payment_success.*/, '');

  if (!selectedMovie) {
    return redirectErrorPage(
      false,
      null,
      {
        tabMenu: 'false',
        back: 'false',
        showTryAgainButton: 'true',
        showHeader: 'false',
        message: INELIGIBLE_ACCESS.message,
        retryUrl: detailPageUrl
      },
      new ClientError(INELIGIBLE_ACCESS.errorCode, INELIGIBLE_ACCESS.message)
    );
  }

  const playResponse = await Consent.getPlay({
    movieId: selectedMovie.id,
    screen
  });

  if (playResponse.data.showSignIn && !selectedMovie.pilot) {
    Router.pushRoute('/ev/signin');
    return;
  }

  if (playResponse.data.showConsent) {
    if (isPaymentRedirect) {
      return redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'false',
          showTryAgainButton: 'true',
          showHeader: 'false',
          message: INELIGIBLE_ACCESS.message,
          retryUrl: detailPageUrl
        },
        new ClientError(INELIGIBLE_ACCESS.errorCode, INELIGIBLE_ACCESS.message)
      );
    }

    openConsentModal && openConsentModal(playResponse.data, gtmPayload);
    return;
  }

  if (playResponse.data.redirect) {
    const { titleId } = playResponse.data;
    Router.pushRoute('plans', { titleId });
    return;
  }

  trackGtm('Playback_Start_Event', {
    ...gtmPayload,
    source_event_name: sourceEventName
  });

  playResponse.data.position = position;
  playResponse.data.duration = duration;
  Consent.nativePlay(playResponse, isHeartbeatEnabled);
};
