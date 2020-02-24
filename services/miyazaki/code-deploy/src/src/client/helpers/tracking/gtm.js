import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();

const APP_VERSION = publicRuntimeConfig.npm_package_version;

export default class Gtm {
  constructor() {
    this.defaultTraits = {};
  }

  static getCommonProperties() {
    return {
      page_title: document.title,
      page_url: window.location.href,
      app_namespace: 'GRAB-WEBVIEW',
      app_version: APP_VERSION
    };
  }

  static repeatAllowed(event) {
    const repeatedEvents = [
      'button_tap_expand_collection',
      'button_tap_seasons',
      'button_tap_channel'
    ];
    return repeatedEvents.includes(event.event);
  }

  static hasBeenPushedAlready(event) {
    const dataLayer = window.dataLayer;
    if (dataLayer.length === 0) {
      return false;
    }

    const lastEvent = dataLayer[dataLayer.length - 1];
    const propsToCompare = ['page_title', 'page_url', 'event', 'cuid', 'cueid'];
    return propsToCompare.every(p => lastEvent[p] === event[p]);
  }

  init(defaultTraits = {}) {
    this.defaultTraits = defaultTraits;
  }

  add(eventType, payload = {}) {
    if (typeof window === 'undefined') {
      return;
    }

    if (!window.dataLayer) {
      return;
    }

    const evtProps = {
      ...this.defaultTraits,
      ...Gtm.getCommonProperties(),
      ...payload,
      event: eventType,
      timestamp: new Date().toISOString()
    };
    if (Gtm.repeatAllowed(evtProps) || !Gtm.hasBeenPushedAlready(evtProps)) {
      window.dataLayer.push(evtProps);
    }
  }
}
