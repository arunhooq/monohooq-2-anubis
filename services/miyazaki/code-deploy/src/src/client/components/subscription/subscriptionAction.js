import React from 'react';
import { connect } from 'react-redux';
import { get } from 'lodash';
import { cancelSubscription as cancelUserSubscription } from '../../services/subscription';
import SubscriptionModal from './subscriptionModal';
import { Subscription } from '../../../common/General';
import { filterActiveSVODSubscriptions } from '../../../common/Subscription';
import { redirectErrorPage } from '../../helpers/redirect';
import { setUserSubscription } from '../../actions/userAction';
import { getDetails as getSubscriptionDetails } from '../../services/subscription';
import './subscriptionAction.scss';

const { ACTIVE, HOOQ_STAFF_SUFFIX } = Subscription;

const mapStateToProps = state => ({
  translation: state.translation,
  userSession: state.user.userSession
});

const mapDispatchToProps = dispatch => ({
  setUserSubscription: subscription =>
    dispatch(setUserSubscription(subscription))
});

class SubscriptionAction extends React.Component {
  state = {
    modalState: false,
    showButton: true
  };

  componentDidMount() {
    const { userSession } = this.props;

    const activeSubscriptions = filterActiveSVODSubscriptions(
      userSession.subscription
    );

    const status = get(activeSubscriptions, '[0].status', '');
    const serviceID = get(activeSubscriptions, '[0].serviceID', '');

    const isSubscriptionActive = status.toUpperCase() === ACTIVE;
    const isNotStaff = !serviceID.includes(HOOQ_STAFF_SUFFIX);
    this.setState({
      showButton: isSubscriptionActive && isNotStaff
    });
  }

  openModal = () => {
    this.setState({ modalState: true });
  };

  closeModal = () => {
    this.setState({ modalState: false });
  };

  cancelCallback = async () => {
    const subscriptionDetails = !this.props.userSession.isVisitor
      ? await getSubscriptionDetails()
      : [];
    this.props.setUserSubscription(subscriptionDetails);
  };

  cancelSubscription = async () => {
    const { userSession } = this.props;

    try {
      const currentSubscription = get(userSession, 'subscription[0]', {});
      await cancelUserSubscription(currentSubscription.serviceID);
      this.cancelCallback();
      this.closeModal();
      this.setState({ showButton: false });
    } catch (error) {
      redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'true',
          retryUrl: '/subscription'
        },
        error
      );
    }
  };

  render() {
    const { translation } = this.props;
    const { modalState, showButton } = this.state;
    return (
      <React.Fragment>
        {showButton && (
          <footer className="subscription-footer">
            <div className="content has-text-centered">
              <button
                className="button subscription-btn"
                onClick={() => this.openModal()}
              >
                <span>{translation.subscription_cancelLabel}</span>
              </button>
            </div>
          </footer>
        )}

        <SubscriptionModal
          open={modalState}
          onCloseModal={this.closeModal}
          onConfirm={this.cancelSubscription}
        />
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubscriptionAction);
