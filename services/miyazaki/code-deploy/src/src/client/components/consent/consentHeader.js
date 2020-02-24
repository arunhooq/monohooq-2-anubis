import React from 'react';
import { connect } from 'react-redux';

const mapStateToProps = state => ({
  translation: state.translation,
  consent: state.detail.consent,
  partnerConfig: state.partner
});

class ConsentHeader extends React.Component {
  render() {
    const { staticAssetUrl } = this.props.partnerConfig.constants;

    return (
      <React.Fragment>
        <div className="consent-header-wrapper">
          <div className="consent-header-left">
            <img
              style={{ marginRight: '8px' }}
              width="59px"
              src={this.props.partnerLogo}
            />
          </div>
          <div className="consent-header-right">
            <img src={`${staticAssetUrl}/images/id/consent/ic_hooq_logo.png`} />
          </div>
        </div>
        <div className="consent-poster">
          <img src={this.props.imgHeader} />
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(ConsentHeader);
