import React from 'react';
import { connect } from 'react-redux';
import LinkTVGuide from './LinkTVGuide';
import LinkTVInputCode from './LinkTVInputCode';

const mapStateToProps = state => ({
  translation: state.translation,
  accessToken: state.user.userSession.session.accessToken,
  email: state.user.userSession.user.email,
  phoneNumber: state.user.userSession.user.phoneNumber,
  countryofLogin: state.user.userSession.geo.countryofLogin,
  isEvWebviewSignin: state.user.userSession.isEvWebviewSignin
});

class LinkTV extends React.Component {
  render() {
    const {
      translation,
      accessToken,
      email,
      phoneNumber,
      countryofLogin,
      isEvWebviewSignin
    } = this.props;
    return (
      <React.Fragment>
        <LinkTVGuide translation={translation} />
        <LinkTVInputCode
          accessToken={accessToken}
          email={email}
          phoneNumber={phoneNumber}
          countryofLogin={countryofLogin}
          translation={translation}
          isEvWebviewSignin={isEvWebviewSignin}
        />
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(LinkTV);
