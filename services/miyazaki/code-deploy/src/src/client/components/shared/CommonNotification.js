import React from 'react';
import { connect } from 'react-redux';
import { Notification } from 'react-notification';
import { setCommonNotification } from '../../actions/pagesAction';
import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();

let INTERVAL_TO_SHOW = parseInt(publicRuntimeConfig.INTERVAL_TO_SHOW, 10);

const mapStateToProps = state => {
  return {
    pages: state.pages
  };
};

const mapDispatchToProps = dispatch => ({
  setCommonNotification: param => dispatch(setCommonNotification(param))
});

class CommonNotification extends React.Component {
  onDismiss = () => {
    const { commonNotification } = this.props.pages;
    const { show } = commonNotification;
    this.props.setCommonNotification({ show: !show });
  };

  render() {
    const { commonNotification } = this.props.pages;
    const { show, message } = commonNotification;
    return (
      <Notification
        className="notification-copy-inactive"
        isActive={show}
        message={message || ''}
        activeClassName="notification-copy-active"
        dismissAfter={INTERVAL_TO_SHOW || 3000}
        style={false}
        onDismiss={() => this.onDismiss()}
      />
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CommonNotification);
