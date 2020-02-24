import React from 'react';
import { connect } from 'react-redux';

const mapStateToProps = state => ({
  partnerConfig: state.partner
});

class HeaderImage extends React.Component {
  render() {
    const { staticAssetUrl } = this.props.partnerConfig.constants;

    return (
      <img
        width="100%"
        src={`${staticAssetUrl}/images/id/planselector/planselector-id.jpg`}
      />
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(HeaderImage);
