const defaultState = {};

export default function translationReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setTranslation':
      return { ...state, ...action.payload };
    default:
      return state;
  }
}
