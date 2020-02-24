import React from 'react';
import { connect } from 'react-redux';
import JsonApiHelper from '../../../common/JsonApiHelper';
import { setSpinnerState } from '../../actions/loadingAction';
import { prepareGtmPayload } from '../../util/trackingPayload';
import { get } from 'lodash';
import { play } from '../../util/play';
import { redirectErrorPage } from '../../helpers/redirect';
import './CTAButton.scss';

const mapStateToProps = (state, ownProps) => {
  return {
    tracking: state.tracking,
    restriction: state.partner.restriction,
    userSession: state.user.userSession,
    screen: state.pages.screen
  };
};

const mapDispatchToProps = dispatch => ({
  setSpinnerState: state => dispatch(setSpinnerState(state))
});

class CTAButton extends React.Component {
  async action(url, opts) {
    const { movie, restriction, userSession, screen } = this.props;
    this.props.tracking.trackGtm(this.props.eventName, { titleId: movie.id });

    if (opts) {
      this.props.setSpinnerState(true);
      if (opts.redirection) {
        window.location.href = url;
      } else {
        try {
          const response = await JsonApiHelper.post({
            path: opts.path,
            payload: opts.payload
          });

          const sourceEventName = 'consent_page';
          const gtmPayload = prepareGtmPayload({ movie });
          const heartBeat = get(restriction, 'playback.heartbeat', false);
          const isHeartbeatEnabled = heartBeat && !userSession.isVisitor;

          await play({
            selectedMovie: movie,
            trackGtm: this.props.tracking.trackGtm,
            sourceEventName,
            gtmPayload,
            screen,
            isHeartbeatEnabled
          });
        } catch (error) {
          redirectErrorPage(
            false,
            null,
            {
              tabMenu: 'false',
              back: 'true',
              showTryAgainButton: 'false'
            },
            error
          );
        } finally {
          this.props.setSpinnerState(false);
        }
      }
    }
  }

  handleClick(callback, url, opts) {
    callback ? callback(url, opts) : this.action(url, opts).then(action => {});
  }

  render() {
    const {
      children,
      color,
      backgroundColor,
      callback,
      url,
      opts
    } = this.props;
    return (
      <React.Fragment>
        <button
          className="button are-large is-fullwidth is-not-outlined"
          style={{ color: color, backgroundColor: backgroundColor }}
          onClick={() => this.handleClick(callback, url, opts)}
        >
          {children}
        </button>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CTAButton);
