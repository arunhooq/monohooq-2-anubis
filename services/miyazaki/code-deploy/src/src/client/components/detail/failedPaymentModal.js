import React from 'react';
import Modal from 'react-responsive-modal';
import { connect } from 'react-redux';
import CTAButton from '../shared/CTAButton';
import './failedPaymentModal.scss';

const mapStateToProps = state => ({
  translation: state.translation,
  tracking: state.tracking,
  config: state.detail.config
});

export class FailedPaymentModal extends React.Component {
  ctaCallback(url, opts) {
    opts.tracking('button_tap_payment_creditcard', opts.eventPayload);
    window.location.href = url;
  }

  render() {
    const {
      toggleModal,
      open,
      paymentUrl,
      config: { failedPaymentModal },
      translation,
      eventPayload
    } = this.props;
    const opts = {
      tracking: this.props.tracking.trackGtm,
      eventPayload
    };

    return (
      <Modal
        classNames={{ modal: 'failed-payment-modal' }}
        open={open || false}
        onClose={toggleModal}
        closeOnOverlayClick={false}
        closeOnEsc={false}
        center
      >
        <div className="failed-payment-modal__wrapper">
          <p className="failed-payment-modal__header">
            {translation.payment_failed_linking}
          </p>
          <p className="failed-payment-modal__content">
            {translation.payment_failed_try}
          </p>

          <div className="failed-payment-modal__cta">
            <CTAButton
              url={paymentUrl}
              color={failedPaymentModal.cta.color}
              backgroundColor={failedPaymentModal.cta.backgroundColor}
              callback={this.ctaCallback}
              opts={opts}
            >
              {failedPaymentModal.cta.text}
            </CTAButton>
          </div>
        </div>
      </Modal>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(FailedPaymentModal);
