/* eslint-disable import/prefer-default-export */

module.exports = {
  ERROR_READING_SERVER_RESPONSE: 'ERROR_READING_SERVER_RESPONSE',
  INVALID_API_KEY: 'INVALID_API_KEY',
  INVALID_JSON_RESPONSE: 'INVALID_JSON_RESPONSE',
  NOVA_API_ERROR: 'NOVA_API_ERROR',
  KONG_INVALID_API_KEY: 'Invalid authentication credentials',
  KONG_MISSING_API_KEY: 'No API key found in request',
  SOMETHING_BAD_HAPPENS: {
    errorCode: 'SHANTI-0000',
    message: 'Something bad happens, please try again later',
    statusCode: 500
  },
  INVALID_SESSION: {
    errorCode: 'SHANTI-4011',
    message: 'Invalid Session!',
    statusCode: 401
  },
  FAILED_GETTING_HISTORY: {
    errorCode: 'SHANTI-4012',
    message: '',
    statusCode: 401
  },
  MISSING_PARAMETERS: {
    errorCode: 'SHANTI-4013',
    message: 'Missing required parameters',
    statusCode: 400
  },
  FAILED_GETTING_WATCHED_MOVIES: {
    errorCode: 'SHANTI-4014',
    message: 'Error when getting watched movies',
    statusCode: 401
  },
  FAILED_GETTING_MOVIE_DETAIL: {
    errorCode: 'SHANTI-4015',
    message: 'Error when getting movie detail',
    statusCode: 401
  },
  GENERIC_PLAY_API_ERROR: {
    errorCode: 'SHANTI-4016',
    message: '',
    statusCode: 401
  },
  UNKNOWN_DEVICE: {
    errorCode: 'SHANTI-6000',
    message: 'Unknown Device'
  },
  UNAUTHENTICATED_COOKIE: {
    errorCode: 'SHANTI-6032',
    message: 'Access is denied due to invalid credentials.',
    statusCode: 401
  },
  INELIGIBLE_ACCESS: {
    errorCode: 'SHANTI-6033',
    message: 'You are not eligible to access this page',
    statusCode: 403
  },
  PRIMARY_ID_NOT_FOUND: {
    errorCode: 'SHANTI-6043',
    message: 'Cannot find primary ID for signin',
    statusCode: 400
  },
  FAILED_SIGNIN: {
    errorCode: 'SHANTI-6048',
    message: 'Failed on signin, please try again',
    statusCode: 400
  },
  FAILED_GETTING_PARTNER_CONFIG: {
    errorCode: 'SHANTI-6202',
    message: 'Cannot get partner configuration',
    statusCode: 500
  },
  INVALID_REQUEST: {
    errorCode: 'SHANTI-7001',
    message: 'Sorry, we are unable to process your request',
    statusCode: 500
  },
  CURATIONS_IS_EMPTY: {
    errorCode: 'SHANTI-7002',
    message: 'Curations is empty',
    statusCode: 404
  },
  DISCOVER_FEEDS_IS_EMPTY: {
    errorCode: 'SHANTI-7003',
    message: 'Discover feeds is empty',
    statusCode: 404
  },
  UNSUPPORTED_COUNTRY: {
    errorCode: 'SHANTI-7031',
    message: 'Your country is not supported',
    statusCode: 403
  },
  COLLECTION_NOT_FOUND: {
    errorCode: 'SHANTI-7004',
    message: 'Collection not found',
    statusCode: 404
  },
  TITLE_NOT_FOUND: {
    errorCode: 'SHANTI-7098',
    message: 'Title not found',
    statusCode: 404
  },
  PAGE_NOT_FOUND: {
    errorCode: 'SHANTI-7099',
    message: 'Page not found',
    statusCode: 404
  },
  UNVERIFIED_ACCOUNT: {
    errorCode: 'SHANTI-9795',
    message: 'Sorry, we could not verify your account',
    statusCode: 400
  },
  INVALID_CONFIGURATION: {
    errorCode: 'SHANTI-9899',
    message: 'Invalid configuration: output has to begin with "@"!',
    statusCode: 500
  },
  GENERIC_ERROR: {
    errorCode: 'SHANTI-9999',
    message: 'Something Bad Happen, please try again later',
    statusCode: 400
  },
  EveErrors: {
    'AU-eV2155': {
      code: 'SHANTI-4017',
      message: 'No free trial Available'
    },
    'AU-111111111': {
      code: 'SHANTI-4018',
      message: 'Authorization failed'
    },
    'AU-eV1003': {
      code: 'SHANTI-4019',
      message: 'Invalid SP Account ID XXXXXXXX for HOOQIND'
    },
    'AU-eV1342': {
      code: 'SHANTI-4020',
      message: 'Invalid customer ID XXXXXXXXX for XXXXXX'
    },
    'AU-eV1638': {
      code: 'SHANTI-4021',
      message:
        "Account doesn't exists with the given SP Account ID XXXXXXXX and Customer ID XXXXXXX"
    },
    'AU-eV2123': {
      code: 'SHANTI-4022',
      message:
        'Either SP Account ID or CP Customer ID or Session Token is required'
    },
    'AU-eV2124': {
      code: 'SHANTI-4023',
      message: 'Invalid SP Account ID XXXXXXXX for HOOQIND'
    },
    'AU-eV2155': {
      code: 'SHANTI-4024',
      message: 'No free trial Available'
    },
    'USR-1008': {
      code: 'SHANTI-4025',
      message: 'Invalid or expired refresh token'
    },
    'USR-AU-eV2767': {
      code: 'SHANTI-4026',
      message: 'Invalid or expired refresh token'
    },
    'USR-Kong-404': {
      code: 'USR-Kong-404',
      message: 'Token expired'
    },
    'Kong-404': {
      code: 'Kong-404',
      message: 'Token expired'
    }
  }
};
