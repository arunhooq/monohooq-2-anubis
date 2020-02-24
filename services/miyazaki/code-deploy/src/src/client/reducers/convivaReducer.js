const defaultState = {
  gatewayUrl: '',
  customerKey: '',
  appName: '',
  enable: false,
  enableLog: false
};

export default function discoverReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setSettings':
      return { ...state, ...action.payload };
    default:
      return state;
  }
}
