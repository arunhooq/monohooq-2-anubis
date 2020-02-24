import React from 'react';
import { connect } from 'react-redux';
import { CSSTransition } from 'react-transition-group';
import IconToastNotification from '../icons/IconToastNotification';
import { setToastNotification } from '../../actions/pagesAction';
import './ToastNotification.scss';

const mapStateToProps = state => ({
  toastNotification: state.pages.toastNotification
});

const mapDispatchToProps = dispatch => ({
  setToastNotification: open => dispatch(setToastNotification(open))
});

class Notification extends React.Component {
  handleClick = () => {
    this.props.setToastNotification({ open: false, title: '', subtitle: '' });
  };

  render() {
    const { toastNotification } = this.props;
    const { open, title } = toastNotification;

    return (
      <React.Fragment>
        <CSSTransition in={open} timeout={300} classNames="toast" unmountOnExit>
          <div className="toast">
            <div className="toast__wrapper" onClick={this.handleClick}>
              <div className="toast__image">
                <IconToastNotification />
              </div>

              <div className="toast__content">
                <div className="toast__title">{title}</div>
                {/* TODO: Uncomment below line when it has been allowed to be released */}
                {/* <div className="toast__subtitle">{subtitle}</div> */}
              </div>
            </div>
          </div>
        </CSSTransition>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Notification);
