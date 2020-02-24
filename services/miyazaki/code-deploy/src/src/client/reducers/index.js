import { combineReducers } from 'redux';
import discoverReducer from './discoverReducer';
import partnerReducer from './partnerReducer';
import convivaReducer from './convivaReducer';
import curationReducer from './curationReducer';
import channelReducer from './channelReducer';
import menuReducer from './menuReducer';
import loadingReducer from './loadingReducer';
import detailReducer from './detailReducer';
import userReducer from './userReducer';
import searchReducer from './searchReducer';
import loggerReducer from './loggerReducer';
import trackingReducer from './trackingReducer';
import pagesReducer from './pagesReducer';
import planReducer from './planReducer';
import translationReducer from './translationReducer';

const reducer = combineReducers({
  discover: discoverReducer,
  partner: partnerReducer,
  convivaSettings: convivaReducer,
  channel: channelReducer,
  menu: menuReducer,
  loading: loadingReducer,
  detail: detailReducer,
  user: userReducer,
  search: searchReducer,
  logger: loggerReducer,
  pages: pagesReducer,
  curation: curationReducer,
  tracking: trackingReducer,
  plan: planReducer,
  translation: translationReducer
});

export default reducer;
