import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();

export const CUSTOM_EVENT = {
  OPEN_POPUP_CONSENT: {
    TAG: 'HooqWebviewEvent',
    TYPE: {
      OPEN: 'CONSENT_POPUP_OPEN',
      ACCEPT_TERM: 'CONSENT_POPUP_ACCEPT_TERM',
      ACCEPT_POLICY: 'CONSENT_POPUP_ACCEPT_POLICY',
      ACCEPT: 'CONSENT_POPUP_ACCEPT',
      DECLINE: 'CONSENT_POPUP_DECLINE'
    }
  }
};

// To view custom events in New Relic, go to Insights -> PageAction (Dropdown) -> Group by ActionName (Dropdown)
export function logCustomEvent(eventType, attributes = {}) {
  if (typeof newrelic == 'object') {
    let defaultAttributes = {
      appName: 'HOOQ-WEBVIEW'
    };
    attributes = Object.assign(attributes, defaultAttributes);
    newrelic.addPageAction(eventType, attributes);
  }
}

export function logError(error) {
  if (typeof newrelic == 'object') {
    newrelic.noticeError(error);
  }
}

export const isEnvVarSet = name => publicRuntimeConfig[name] !== undefined;
export const isNewRelicEnabled = () =>
  publicRuntimeConfig.ENABLE_NEWRELIC === 'true';

const defaultOptions = {
  licenseKey: '',
  applicationID: ''
};

/** Generate the NREUM based on the environment variables, if the ENABLE_NEWRELIC is set to true.
 * This NREUM should be put after the nr agent code snippet script tag.
 * @param {object} options
 * @param {string} options.applicationID - The New Relic Browser app identifier
 * @param {string} options.licenseKey - The New Relic Browser license key
 * @returns {string} If ENABLE_NEWRELIC enabled return the minified NREUM
 */
export function generateMinifiedNREUM(options = defaultOptions) {
  const shouldGenerateTheNREUM =
    isNewRelicEnabled() &&
    isEnvVarSet('NEWRELIC_BROWSER_APPLICATION_ID') &&
    isEnvVarSet('NEWRELIC_BROWSER_LICENSE_KEY');

  if (shouldGenerateTheNREUM) {
    options.applicationID = publicRuntimeConfig.NEWRELIC_BROWSER_APPLICATION_ID;
    options.licenseKey = publicRuntimeConfig.NEWRELIC_BROWSER_LICENSE_KEY;
    // prettier-ignore
    return `window.NREUM||(NREUM={}),window.NREUM.info={licenseKey:"${options.licenseKey}",applicationID:"${options.applicationID}",sa:1};`;
  }

  return ``;
}
