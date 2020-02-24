import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../routes';
import Header from '../components/shared/Header';
import { setVisibility } from '../actions/menuAction';
import { setInitialProps } from '../util/pages';
import '../components/error/error.scss';
import ErrorCodes from './../../common/ErrorCodes';

const mapStateToProps = state => ({
  menu: state.menu,
  translation: state.translation
});

const mapDispatchToProps = dispatch => ({
  setVisibility: visible => dispatch(setVisibility(visible))
});

class Error extends React.Component {
  static getErrorMessage(errorCodes, code) {
    for (let value in errorCodes) {
      const err = errorCodes[value];
      if (err.errorCode !== undefined && err.errorCode === code) {
        return err.message;
      }
    }

    return '';
  }

  static async getInitialProps(ctx) {
    const { query, res, err } = ctx;
    ctx.query.skipPartnerConfig = 'true';
    await setInitialProps(ctx);

    const statusCode = res ? res.statusCode : err ? err.statusCode : null;
    const tabMenu = query.tabMenu || 'false';
    const back = query.back || 'false';
    const retryUrl = query.retryUrl || '/';
    const message =
      this.getErrorMessage(ErrorCodes, query.code) || query.message || '';
    const showTryAgainButton =
      query.showTryAgainButton === 'false' ? false : true;
    const showHeader = query.showHeader === 'false' ? false : true;

    return {
      statusCode,
      tabMenu,
      back,
      retryUrl,
      message,
      showTryAgainButton,
      showHeader
    };
  }

  onRedirect(url) {
    Router.pushRoute(url);
  }

  componentWillMount() {
    const visible = this.props.tabMenu === 'true' ? true : false;
    this.props.setVisibility(visible);
  }

  renderTryAgainButton(flag, retryUrl) {
    if (flag) {
      return (
        <div className="error__wrapper--center">
          <button
            className="error__button"
            onClick={() => this.onRedirect(retryUrl)}
          >
            <span className="error__button-text">Try again</span>
          </button>
        </div>
      );
    }
  }

  render() {
    const {
      back,
      message,
      showTryAgainButton,
      retryUrl,
      showHeader
    } = this.props;
    let errMessage = message !== '' ? message : '';

    return (
      <React.Fragment>
        {showHeader && <Header showBack={back === 'true' ? true : false} />}
        <div className="section error__section">
          <div className="error__container">
            <div className="error__wrapper--center">
              <img className="error__logo" src="/static/img/img-error.png" />
            </div>

            <div className="error__message-wrapper">
              <span className="error__title">Something went wrong</span>
            </div>

            <div className="error__message-wrapper">
              <span className="error__subtitle">
                {errMessage ||
                  'Your request was not successful. \n\nPlease try again'}
              </span>
            </div>

            {this.renderTryAgainButton(showTryAgainButton, retryUrl)}
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Error);
