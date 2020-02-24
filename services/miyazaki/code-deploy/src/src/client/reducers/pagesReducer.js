const defaultState = {
  scrollPosition: {},
  errorModal: {
    open: false,
    code: ''
  },
  toastNotification: {
    open: false,
    title: '',
    subtitle: ''
  },
  commonNotification: {
    show: false,
    message: ''
  },
  screen: {
    width: 0,
    heigth: 0,
    orientation: ''
  },
  currentPage: ''
};

export default function pagesReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setScrollPosition':
      const { pathname, x, y } = action.payload;
      const scrollPosition = state.scrollPosition || {};
      return {
        ...state,
        scrollPosition: {
          ...scrollPosition,
          [pathname]: { x, y }
        }
      };
    case 'setErrorModal':
      return { ...state, errorModal: action.payload };
    case 'setToastNotification':
      return { ...state, toastNotification: action.payload };
    case 'setCommonNotification':
      return { ...state, commonNotification: action.payload };
    case 'setScreenDimensions':
      return { ...state, screen: action.payload };
    case 'setCurrentPage':
      return { ...state, currentPage: action.payload };
    default:
      return state;
  }
}
