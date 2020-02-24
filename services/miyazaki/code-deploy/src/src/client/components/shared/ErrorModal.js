import React from 'react';
import Modal from 'react-responsive-modal';
import { connect } from 'react-redux';
import get from 'lodash.get';
import CTAButton from './CTAButton';
import { setErrorModal } from '../../actions/pagesAction';
import { SOMETHING_BAD_HAPPENS } from '../../../common/ErrorCodes';
import './ErrorModal.scss';

const mapStateToProps = state => ({
  translation: state.translation,
  errorModal: state.pages.errorModal,
  uiConfig: state.partner.uiConfig
});

const mapDispatchToProps = dispatch => ({
  setErrorModal: open => dispatch(setErrorModal(open))
});

export class ErrorModal extends React.Component {
  handleClick = () => {
    const { code } = this.props.errorModal;
    this.props.setErrorModal({ open: false, code });
  };

  render() {
    const { translation, errorModal, uiConfig } = this.props;
    const message = get(translation, `${errorModal.code}`, '');
    const fallbackMessage = get(
      translation,
      SOMETHING_BAD_HAPPENS.errorCode,
      ''
    );
    const cta = get(uiConfig, 'errorModal.cta');

    return (
      <Modal
        classNames={{ modal: 'alert-modal' }}
        open={errorModal.open || false}
        onClose={() => {}} // Note: Pass empty arrow function since the onClose prop is required
        showCloseIcon={false}
        closeOnOverlayClick={false}
        closeOnEsc={false}
        center
      >
        <div className="alert-content">{message || fallbackMessage}</div>

        <div className="alert-button">
          {cta &&
            cta.map((btn, index) => {
              const btnText = get(translation, btn.label, '');

              return (
                <div className="cta-alert-btn" key={index}>
                  <CTAButton
                    backgroundColor={btn.color}
                    color={btn.fontColor}
                    eventName={btn.eventName}
                    opts={btn.opts}
                    callback={this.handleClick}
                  >
                    {btnText}
                  </CTAButton>
                </div>
              );
            })}
        </div>
      </Modal>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ErrorModal);
