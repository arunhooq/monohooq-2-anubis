import React from 'react';
import { connect } from 'react-redux';
import { get } from 'lodash';
import { changeFormatDate, getPlanTitle } from '../../helpers/subscription';
import { Subscription } from '../../../common/General';
import './subscriptionInfo.scss';

const { ACTIVE, HOOQ_STAFF_SUFFIX } = Subscription;

const mapStateToProps = state => ({
  translation: state.translation,
  userSession: state.user.userSession
});

class SubscriptionInfo extends React.Component {
  state = {
    serviceID: '',
    planTitle: '',
    dayRemaining: 0,
    isRenewal: false,
    dueDate: '',
    paymentMethod: '-',
    isSubscriptionActive: false,
    isFreeUser: false,
    isHooqStaff: false
  };

  async componentDidMount() {
    const { translation, userSession } = this.props;

    const currentSubscription = get(userSession, 'subscription[0]', {});
    const serviceID = get(currentSubscription, 'serviceID', '');
    const isFreeUser = serviceID !== '' ? false : true;
    const isHooqStaff = serviceID.includes(HOOQ_STAFF_SUFFIX) ? true : false;

    const planTitle = isHooqStaff
      ? translation.subscription_hooqStaff
      : isFreeUser
      ? translation.subscription_freeUser
      : getPlanTitle(currentSubscription, translation);
    const { dayRemaining, isRenewal, validityTill } = currentSubscription;
    const isSubscriptionActive =
      currentSubscription.status &&
      currentSubscription.status.toUpperCase() === ACTIVE;
    const formattedDate = changeFormatDate(validityTill);
    const dueDate = formattedDate !== '' ? `(${formattedDate})` : '';
    const paymentMethod =
      userSession.status === ACTIVE ? userSession.paymentMethod : null;

    this.setState({
      serviceID,
      planTitle,
      dayRemaining,
      isRenewal,
      dueDate,
      paymentMethod,
      isSubscriptionActive,
      isFreeUser,
      isHooqStaff
    });
  }

  render() {
    const { translation } = this.props;
    const {
      planTitle,
      dayRemaining,
      isRenewal,
      dueDate,
      paymentMethod,
      isSubscriptionActive,
      isFreeUser,
      isHooqStaff
    } = this.state;

    return (
      <React.Fragment>
        <div className="columns is-mobile subscription-info-header">
          <div className="column">
            <span>{translation.subscription_title}</span>
            <p />
            <span>{planTitle}</span>
          </div>
        </div>

        <div className="subscription-info-table">
          <div className="columns is-mobile subscription-info-row">
            <div
              className="column is-half is-left"
              style={{ textAlign: 'left' }}
            >
              {isRenewal
                ? isSubscriptionActive
                  ? translation.subscription_renewalIn
                  : translation.subscription_cancelStatus
                : translation.subscription_valid_until}
            </div>
            <div
              className="column is-half is-right"
              style={{ textAlign: 'left' }}
            >
              {isHooqStaff || isFreeUser
                ? '-'
                : `${dayRemaining} ${translation.subscription_day} ${dueDate}`}
            </div>
          </div>

          {!isHooqStaff && !isFreeUser && (
            <div className="columns is-mobile subscription-info-row">
              <div
                className="column is-half is-left"
                style={{ textAlign: 'left' }}
              >
                {translation.subscription_renewal}
              </div>
              <div
                className="column is-half is-right"
                style={{ textAlign: 'left' }}
              >
                {isRenewal ? 'ON' : 'OFF'}
              </div>
            </div>
          )}

          {!isHooqStaff && !isFreeUser && paymentMethod && (
            <div className="columns is-mobile subscription-info-row">
              <div
                className="column is-half is-left"
                style={{ textAlign: 'left' }}
              >
                {translation.subscription_paymentWith}
              </div>
              <div
                className="column is-half is-right"
                style={{ textAlign: 'left' }}
              >
                {paymentMethod}
              </div>
            </div>
          )}
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(SubscriptionInfo);
