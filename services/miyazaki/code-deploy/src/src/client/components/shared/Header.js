import React from 'react';
import { Router } from './../../routes';
import './Header.scss';

const BackButton = props => {
  const containerStyles = {
    paddingLeft: '1rem',
    paddingTop: '0.75rem',
    position: 'absolute',
    height: '100%',
    verticalAlign: 'middle',
    top: 0,
    left: 0
  };
  return (
    <div
      style={containerStyles}
      onClick={() => props.onClick(props.backToHome)}
    >
      <img
        className="button-back"
        src="/static/img/ic-back.svg"
        alt="back"
        width="24px"
        height="24px"
      />
    </div>
  );
};

const CloseButton = props => {
  const containerStyles = {
    paddingLeft: '1rem',
    paddingTop: '0.75rem',
    position: 'absolute',
    height: '100%',
    verticalAlign: 'middle',
    top: 0,
    left: 0
  };

  return (
    <div style={containerStyles} onClick={() => props.onClick()}>
      <img
        className="button-close"
        onClick={() => props.onClick()}
        src="/static/img/ic-close.svg"
        alt="back"
        width="24px"
        height="24px"
      />
    </div>
  );
};

class Header extends React.Component {
  back = (path = false) => {
    if (path) {
      Router.pushRoute('discover');
    } else {
      Router.back();
    }
  };

  close = () => {
    if (window.PlatformKit) {
      // Android
      window.PlatformKit.back();
    } else if (window.webkit) {
      // iOS
      window.webkit.messageHandlers.PlatformKit.postMessage('back');
    } else {
      console.log('Platform Kit not found');
    }
  };

  render() {
    const {
      backToHome,
      children,
      showBack,
      showClose,
      headerWrapperClass = '',
      pageTitleClass = ''
    } = this.props;

    return (
      <React.Fragment>
        <div className={`container header-wrapper ${headerWrapperClass}`}>
          {showBack && (
            <BackButton backToHome={backToHome} onClick={this.back} />
          )}

          {showClose && <CloseButton onClick={this.close} />}

          <div className={`page-title ${pageTitleClass}`}>{children}</div>
        </div>
        <div style={{ marginTop: '3rem' }} />
      </React.Fragment>
    );
  }
}

export default Header;
