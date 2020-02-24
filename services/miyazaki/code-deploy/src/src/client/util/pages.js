import get from 'lodash.get';
import isEmpty from 'lodash.isempty';
import { redirectErrorPage } from '../helpers/redirect';
import { setLoadingState } from '../actions/loadingAction';
import { setRegion } from '../actions/discoverAction';
import { setTranslation } from '../actions/translationAction';
import { setUserSession } from '../actions/userAction';
import { setSettings } from '../actions/convivaAction';
import JsonApiHelper from '../../common/JsonApiHelper';
import { configMapper, partnerDataMapper } from '../helpers/configMapper';

export const setInitialProps = async ({
  store,
  res,
  query,
  isServer,
  isLoading = true
}) => {
  try {
    const region = isServer ? query.region : store.getState().discover.region;
    const translation = store.getState().translation;
    const userSession = isServer
      ? query.userSession
      : store.getState().user.userSession;

    store.dispatch(setLoadingState(isLoading));
    store.dispatch(setRegion(region));
    store.dispatch(setUserSession(userSession));

    if (query.skipPartnerConfig !== 'true') {
      const partnerConfig = isServer
        ? partnerDataMapper(query.partnerConfig)
        : store.getState().partner;

      const { conviva, translation: translationUrl } = partnerConfig.constants;

      configMapper(partnerConfig, store);

      if (isEmpty(translation) && region !== undefined) {
        let jsonTranslation = await JsonApiHelper.get({ path: translationUrl });
        store.dispatch(setTranslation(jsonTranslation));
      }

      if (conviva.enable) {
        const convivaSettings = {
          gatewayUrl: conviva.gatewayUrl,
          customerKey: conviva.customerKey,
          appName: conviva.appName,
          enable: conviva.enable,
          enableLog: conviva.enableLog
        };
        store.dispatch(setSettings(convivaSettings));
      }
    }

    return;
  } catch (err) {
    redirectErrorPage(
      isServer,
      res,
      '/error',
      {
        tabmenu: false,
        back: false,
        showTryAgainButton: false,
        message: err.message,
        skipPartnerConfig: true
      },
      err
    );
  }
};

export const injectExternalStylesheet = asset => {
  if (typeof document === 'undefined') {
    return;
  }
  const { id, url } = asset;
  if (!isStyleLinkAdded(asset.id)) {
    let head = document.getElementsByTagName('head')[0];
    let link = document.createElement('link');
    link.id = id;
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = url;
    link.media = 'all';
    head.appendChild(link);
  }
};

export const injectExternalScriptTag = (...scripts) => {
  if (typeof document === 'undefined') {
    return;
  }

  return scripts.map(script => loadScript(script));
};

export const setViewPortPosition = (isLoading, pages, pathname) => {
  const x = get(pages, `scrollPosition[${pathname}].x`, 0);
  const y = get(pages, `scrollPosition[${pathname}].y`, 0);
  !isLoading && window.scrollTo(x, y);
};

function loadScript(asset) {
  if (!isScriptAdded(asset.id)) {
    let scriptTag = document.createElement('script');
    scriptTag.id = asset.id;
    scriptTag.src = asset.url;
    scriptTag.async = false;
    document.head.appendChild(scriptTag);
  }
}

function isScriptAdded(id) {
  const scripts = [...document.scripts];
  const result = scripts.filter(script => script.id === id);
  return result.length > 0 ? true : false;
}

function isStyleLinkAdded(id) {
  const result = [...document.querySelectorAll('link')].filter(
    link => link.id === id
  );
  return result.length > 0 ? true : false;
}
