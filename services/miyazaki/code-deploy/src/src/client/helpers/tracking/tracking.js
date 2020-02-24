import { get } from 'lodash';
import Gtm from './gtm';

const isClient = () => {
  return typeof window === 'object';
};

const getScreenDimension = () => {
  return `${window.innerWidth}x${window.innerHeight}`;
};

const getActiveSKU = userSession => {
  if (!userSession) {
    return '';
  }

  const subscriptions = userSession.subscription
    ? userSession.subscription.filter(s => s.status === 'Active')
    : [];
  return subscriptions.length > 0 ? subscriptions[0].serviceID : '';
};

const gtm = new Gtm();
const tracking = {
  gtm,
  setUserSession: function(userSession) {
    if (isClient()) {
      tracking.gtm.init({
        country: get(userSession, 'geo.countryofLogin'),
        evdevice_id: get(userSession, 'user.deviceId'),
        screen_dimension: getScreenDimension(),
        sku: getActiveSKU(userSession),
        evid: get(userSession, 'user.spAccountId'),
        partnerId: get(userSession, 'user.partnerId'),
        device_os: get(userSession, 'device.os'),
        device_os_version: get(userSession, 'device.osVersion')
      });
    }
  },
  trackGtm: function(eventType, payload) {
    if (isClient()) {
      tracking.gtm.add(eventType, payload);
    }
  }
};

export default tracking;
