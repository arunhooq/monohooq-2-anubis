import { DISCOVER } from '../constants/tabMenu';

const defaultState = {
  tab: DISCOVER,
  visible: false,
  sidebar: false,
  cast: false
};

export default function discoverReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setMenuTab':
      return { ...state, tab: action.payload };
    case 'setVisibility':
      return { ...state, visible: action.payload };
    case 'setSidebar':
      return { ...state, sidebar: action.payload };
    case 'setCast':
      return { ...state, cast: action.payload };
    default:
      return state;
  }
}
