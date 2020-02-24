const defaultState = {
  partnerDetail: {},
  constants: {},
  restriction: {},
  uiConfig: {},
  actions: {}
};

export default function partnerReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setPartnerDetail':
      return { ...state, partnerDetail: action.payload };
    case 'setConstants':
      return { ...state, constants: action.payload };
    case 'setRestriction':
      return { ...state, restriction: action.payload };
    case 'setUIConfig':
      return { ...state, uiConfig: action.payload };
    case 'setActions':
      return { ...state, actions: action.payload };
    default:
      return state;
  }
}
