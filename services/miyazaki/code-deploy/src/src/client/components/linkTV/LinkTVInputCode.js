import React from 'react';
import { connect } from 'react-redux';
import { setCommonNotification } from '../../actions/pagesAction';
import { setCast } from '../../actions/menuAction';
import get from 'lodash.get';
import DevicePairingService from './../../helpers/devicePairing';
import './LinkTVGuide.scss';

const mapStateToProps = state => ({
  translation: state.translation,
  devicePairingServiceUrl: state.partner.constants.devicePairingServiceUrl,
  pages: state.pages,
  tracking: state.tracking
});

const mapDispatchToProps = dispatch => ({
  setCommonNotification: param => dispatch(setCommonNotification(param)),
  setCast: shouldOpen => dispatch(setCast(shouldOpen))
});

const DIGIT_OF_CODE = 6;

class LinkTVInputCode extends React.Component {
  state = {
    code: ''
  };

  componentDidMount() {
    const {
      devicePairingServiceUrl,
      translation,
      pages,
      tracking
    } = this.props;

    const validationCallback = (err, result) => {
      let message;
      if (err) {
        tracking.trackGtm('failed_linktv_input_code', {
          source_event_name: `${pages.currentPage}_page`
        });

        message = get(translation, 'linkTV_toast_failed_title', '');
      } else {
        tracking.trackGtm('button_tap_linktv_tvcode', {
          source_event_name: `${pages.currentPage}_page`
        });
        tracking.trackGtm('success_linktv_input_code', {
          source_event_name: `${pages.currentPage}_page`
        });

        message = get(translation, 'linkTv_toast_success_title', '');
        this.props.setCast(false);
      }
      this.displayMessage(message);
    };

    this.devicePairingService = new DevicePairingService(
      devicePairingServiceUrl,
      validationCallback
    );
  }

  componentWillUnmount() {
    this.props.setCast(false);
    if (this.devicePairingService && this.devicePairingService.socket) {
      this.devicePairingService.socket.close();
    }
  }

  generateCredentialsJWTAPI() {
    const {
      email,
      phoneNumber,
      accessToken,
      countryofLogin,
      isEvWebviewSignin
    } = this.props;

    if (isEvWebviewSignin) {
      return {
        phoneNumber: phoneNumber,
        email: email,
        country: countryofLogin,
        token: accessToken
      };
    }

    // TODO: This is hardcoded for Grab. Should come from `partnerConfig.actions.primaryId.extractFromField`.
    return {
      email: email,
      country: countryofLogin,
      token: accessToken
    };
  }

  verifyCode = () => {
    const validateRequestPayload = {
      credentials: this.generateCredentialsJWTAPI(this.props),
      key: this.state.code
    };
    this.devicePairingService.validateKey(validateRequestPayload);
  };

  isCodeAcceptable() {
    const { code } = this.state;
    const stringCode = code.toString();
    return stringCode.length === DIGIT_OF_CODE;
  }

  handleInputChange(e) {
    const { value, maxLength } = event.target;
    const val = value.slice(0, maxLength);
    this.setState({ code: val });
  }

  displayMessage(message) {
    this.props.setCommonNotification({
      show: true,
      message
    });
  }

  render() {
    const { code } = this.state;
    const { translation } = this.props;
    return (
      <div className="link-tv__input_wrapper">
        <input
          maxLength="6"
          id="code-input"
          value={code}
          className="link-tv__input"
          onChange={this.handleInputChange.bind(this)}
          autoComplete="off"
          autoCorrect="off"
          spellCheck={false}
        />
        <button
          className={this.isCodeAcceptable() ? 'active' : ''}
          disabled={!this.isCodeAcceptable()}
          onClick={() => this.verifyCode()}
        >
          {translation.link_btn}
        </button>
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LinkTVInputCode);
