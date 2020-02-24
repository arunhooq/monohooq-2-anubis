import React from 'react';
import { connect } from 'react-redux';
import Header from '../components/shared/Header';
import HooqLoader from '../components/shared/HooqLoader';
import SubscriptionInfo from '../components/subscription/subscriptionInfo';
import SubscriptionAction from '../components/subscription/subscriptionAction';
import { setInitialProps } from '../util/pages';
import { setVisibility } from '../actions/menuAction';
import { setUserSubscription } from '../actions/userAction';
import { getDetails as getSubscriptionDetails } from './../services/subscription';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import '../components/subscription/subscription.scss';

const mapStateToProps = state => ({
  translation: state.translation,
  userSession: state.user.userSession
});

const mapDispatchToProps = dispatch => ({
  setVisibility: state => dispatch(setVisibility(state)),
  setUserSubscription: subscription =>
    dispatch(setUserSubscription(subscription))
});

class Subscription extends React.Component {
  state = {
    isLoading: false
  };

  static async getInitialProps(ctx) {
    await setInitialProps(ctx);
  }

  componentWillMount() {
    this.setState({ isLoading: true });
    this.props.setVisibility(false);
  }

  async componentDidMount() {
    document.title = 'Subscription';
    const subscriptionDetails = !this.props.userSession.isVisitor
      ? await getSubscriptionDetails()
      : [];
    this.props.setUserSubscription(subscriptionDetails);
    this.setState({ isLoading: false });
  }

  renderSubscriptionInfo() {
    return (
      <React.Fragment>
        <div
          className="section"
          style={{ padding: '1rem', height: 'calc(100vh - 3rem)' }}
        >
          <div
            className="container"
            style={{ display: 'flex', flexDirection: 'column', height: '100%' }}
          >
            <SubscriptionInfo />
            <SubscriptionAction />
          </div>
        </div>
      </React.Fragment>
    );
  }

  render() {
    const { translation, userSession } = this.props;
    const { isLoading } = this.state;

    return (
      <React.Fragment>
        <PageLoadTracker trackingTag="subscription" userSession={userSession} />

        <Header
          headerWrapperClass="header-wrapper--subscription"
          pageTitleClass="page-title--subscription"
          showBack
        >
          {translation.header_back}
        </Header>
        {isLoading ? <HooqLoader /> : this.renderSubscriptionInfo()}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Subscription);
