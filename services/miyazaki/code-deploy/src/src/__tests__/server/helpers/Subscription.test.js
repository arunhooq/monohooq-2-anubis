const {
  isEligibleForFreeTrial,
  checkStatus
} = require('../../../server/Helpers/Subscription');
const {
  mockedSubscription,
  mockedEmptySubscription,
  mockedActiveSubscription,
  mockedInactiveSubscription,
  mockedFinalBillSubscription,
  mockedEligibleForTrial,
  mockedIneligibleForTrial
} = require('../../../__mocks__/Subscription');
const { Subscription } = require('../../../common/General');
const {
  filterActiveSVODSubscriptions
} = require('../../../common/Subscription');

describe('filterActiveSVODSubscriptions', () => {
  it('should get active SVOD subscriptions', () => {
    let activeSVODSubscriptions = [];
    if (mockedSubscription.data && mockedSubscription.data.length) {
      activeSVODSubscriptions = filterActiveSVODSubscriptions(
        mockedSubscription.data
      );
    }

    expect(activeSVODSubscriptions.length).toBeGreaterThan(0);
  });

  it('should not get active SVOD subscriptions', () => {
    let emptySVODSubscriptions = [];
    if (mockedEmptySubscription.data && mockedEmptySubscription.data.length) {
      emptySVODSubscriptions = filterActiveSVODSubscriptions(
        mockedEmptySubscription.data
      );
    }

    expect(emptySVODSubscriptions.length).toEqual(0);
  });
});

describe('isEligibleForFreeTrial', () => {
  it('should return true if user is eligible for trial', () => {
    const eligibility = isEligibleForFreeTrial(null, mockedEligibleForTrial);

    expect(eligibility).toBeTruthy();
  });

  it('should return false if user is ineligible for trial', () => {
    const eligibility = isEligibleForFreeTrial(null, mockedIneligibleForTrial);

    expect(eligibility).toBeFalsy();
  });
});

describe('checkStatus', () => {
  test('should return "ACTIVE" when there is a subscription which status is "Active"', () => {
    const status = checkStatus(
      null,
      mockedActiveSubscription,
      mockedIneligibleForTrial
    );

    expect(status).toEqual(Subscription.ACTIVE);
  });

  test('should return "ACTIVE" when there is a subscription which status is "Final Bill"', () => {
    const status = checkStatus(
      null,
      mockedFinalBillSubscription,
      mockedIneligibleForTrial
    );

    expect(status).toEqual(Subscription.ACTIVE);
  });

  test('should return "LAPSED" when there isn\'t an active subscriptions and ineligible for free trial', () => {
    const status = checkStatus(
      null,
      mockedInactiveSubscription,
      mockedIneligibleForTrial
    );

    expect(status).toEqual(Subscription.LAPSED);
  });

  test('should return "NEW_USER" when there isn\'t an active subscriptions and eligible for free trial', () => {
    const status = checkStatus(
      null,
      mockedInactiveSubscription,
      mockedEligibleForTrial
    );

    expect(status).toEqual(Subscription.NEW_USER);
  });
});
