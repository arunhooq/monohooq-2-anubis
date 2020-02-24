export const setScrollPosition = scrollPosition => ({
  type: 'setScrollPosition',
  payload: scrollPosition
});

export const setErrorModal = errorModal => ({
  type: 'setErrorModal',
  payload: errorModal
});

export const setToastNotification = toastNotification => ({
  type: 'setToastNotification',
  payload: toastNotification
});

export const setCommonNotification = commonNotification => ({
  type: 'setCommonNotification',
  payload: commonNotification
});

export const setScreenDimensions = dimensions => ({
  type: 'setScreenDimensions',
  payload: dimensions
});

export const setCurrentPage = page => ({
  type: 'setCurrentPage',
  payload: page
});
