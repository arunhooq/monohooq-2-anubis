export const setUserSession = userSession => ({
  type: 'setUserSession',
  payload: userSession
});

export const setUserSubscription = subscription => ({
  type: 'setUserSubscription',
  payload: subscription
});
