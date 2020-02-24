module.exports = {
  CollectionTitleType: {
    MOVIE: 'MOVIE',
    TVSHOW: 'TVSHOW'
  },
  CustomerType: {
    /** Anonymous: First Time on HOOQ */
    ANONYMOUS: 0,
    /**  Returning: Anonymous Returning with no sign up (Check if _ga cookie exists and no sign up */
    ANONYMOUS_RETURNING: 1,
    /** Signed in with no SKU ever */
    SIGNED_IN_NO_SKU: 2,
    /** Lapsed: isSubscriptionExpired is True and never had free trial */
    SIGNED_IN_LAPSED: 3,
    /** Free Trial: active but on free trial (isUserFreeTrial) */
    SIGNED_IN_FREE_TRIAL: 4,
    /** Active - 1 Time: isRecurringSubscription is False and isSubscriptionExpired is False */
    SIGNED_IN_ACTIVE_ONE_TIME: 5,
    /** Active - Recurring: isRecurringSubscription is True and isSubscriptionExpired is False */
    SIGNED_IN_ACTIVE_RECURRING: 6
  },
  LoginStatus: {
    ANONYMOUS: 'ANONYMOUS',
    SIGNED_IN: 'SIGNED_IN'
  },
  HttpMethod: {
    GET: 'GET',
    POST: 'POST',
    PUT: 'PUT',
    PATCH: 'PATCH',
    DELETE: 'DELETE'
  },
  Subscription: {
    ACTIVE: 'ACTIVE',
    LAPSED: 'LAPSED',
    NEW_USER: 'NEW_USER',
    HOOQ_STAFF_SUFFIX: '-HooqStaff'
  },
  DurationUnit: {
    monthly: 'monthly',
    daily: 'daily'
  },
  CTAType: {
    CREATE_PAYMENT_URL: 'CREATE_PAYMENT_URL',
    CREATE_PLAN_URL: 'CREATE_PLAN_URL',
    DIRECT_ACTIVATION: 'DIRECT_ACTIVATION',
    CANCEL: 'CANCEL'
  }
};
