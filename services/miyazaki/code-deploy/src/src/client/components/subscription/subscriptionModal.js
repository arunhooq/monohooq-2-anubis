import React from 'react';
import Modal from 'react-responsive-modal';
import { connect } from 'react-redux';
import './subscriptionModal.scss';

const mapStateToProps = state => ({
  translation: state.translation
});

class SubscriptionModal extends React.Component {
  render() {
    const { translation } = this.props;
    const { open, onCloseModal, onConfirm } = this.props;
    return (
      <Modal
        classNames={{ modal: 'subscription-modal' }}
        open={open || false}
        onClose={onCloseModal}
        showCloseIcon={false}
        center
      >
        <div className="subscription-content-wrapper">
          <span className="subscription-modal-header">
            {translation.subscription_cancel_modalConfirm}
          </span>
          <span className="subscription-modal-content">
            {translation.subscription_cancel_modalLabel}
          </span>
          <div className="subscription-btn-wrapper">
            <div onClick={() => onCloseModal()}>
              {translation.subscription_cancel_modalNo}
            </div>
            <div onClick={() => onConfirm()}>
              {translation.subscription_cancel_modalYes}
            </div>
          </div>
        </div>
      </Modal>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(SubscriptionModal);
