import set from 'lodash.set';

const defaultState = {
  userSession: {
    session: {
      accessToken: ''
    },
    device: {
      serialNo: '',
      name: '',
      type: '',
      modelNo: '',
      brand: '',
      os: '',
      osVersion: ''
    },
    user: {
      email: '',
      phoneNumber: '',
      accountNumber: '',
      registerNumber: '',
      cpCustomerId: '',
      billingMode: '',
      billDeliveryType: '',
      accountStatus: '',
      accountType: '',
      dmaId: '',
      dmaName: '',
      salesMethod: '',
      directAccount: '',
      country: '',
      status: '',
      deviceId: '',
      spAccountId: '',
      partnerId: '',
      phoneNumber: ''
    },
    geo: {
      countryOfRegistration: '',
      countryofLogin: '',
      ip: ''
    },
    status: '',
    subscription: [],
    isEvWebviewSignin: false,
    isVisitor: false
  }
};

export default function userReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setUserSession':
      return { ...state, userSession: action.payload };
    case 'setUserSubscription':
      const currentState = { ...state };
      set(currentState, 'userSession.status', action.payload.status);
      set(
        currentState,
        'userSession.paymentMethod',
        action.payload.paymentMethod
      );
      set(
        currentState,
        'userSession.subscription',
        action.payload.subscription
      );
      return currentState;
    default:
      return state;
  }
}
