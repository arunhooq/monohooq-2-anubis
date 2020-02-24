import '../styles/index.scss';

import React from 'react';
import getConfig from 'next/config';
import { Provider } from 'react-redux';
import { createStore, compose } from 'redux';
import { enableBatching } from 'redux-batched-actions';
import App, { Container } from 'next/app';
import withRedux from 'next-redux-wrapper';
import { CookiesProvider } from 'react-cookie';
import { PageTransition } from 'next-page-transitions';
import logger, { nr } from '../../common/ClientLogger';
import { Router } from '../routes';
import reducers from '../reducers/index';
import TopPanel from '../components/search/topPanel';
import HooqLoader from '../components/shared/HooqLoader';
import Sidebar from '../components/shared/Sidebar';
import ToastNotification from '../components/shared/ToastNotification';
import ErrorModal from '../components/shared/ErrorModal';
import TabMenu from '../components/tabMenu';
import { setLoggerState } from '../actions/loggerAction';
import {
  setScrollPosition,
  setScreenDimensions,
  setCurrentPage
} from '../actions/pagesAction';
import { setTrackingState } from '../actions/trackingAction';
import { heartbeat } from '../helpers/bridge/heartbeat';
import tracking from '../helpers/tracking/tracking';
import { getCurrentPage } from '../helpers/url';
import { getScreenDimensions } from './../util/common';
import Drawer from '../components/shared/Drawer';
import LinkTV from '../components/linkTV/LinkTV';
import CustomNoSSR from '../components/shared/CustomNoSSR';
import CommonNotification from '../components/shared/CommonNotification';

logger.fuse(nr);

const { publicRuntimeConfig } = getConfig();

/**
 * @param {object} initialState
 * @param {boolean} options.isServer indicates whether it is a server side or client side
 * @param {Request} options.req NodeJS Request object (not set when client applies initialState from server)
 * @param {Request} options.res NodeJS Request object (not set when client applies initialState from server)
 * @param {boolean} options.debug User-defined debug mode param
 * @param {string} options.storeKey This key will be used to preserve store in global namespace for safe HMR
 */
const makeStore = (initialState, options) => {
  let store;
  store = createStore(enableBatching(reducers), initialState);

  if (publicRuntimeConfig.NODE_ENV !== 'production') {
    const devtools =
      process.browser && window.__REDUX_DEVTOOLS_EXTENSION__
        ? window.__REDUX_DEVTOOLS_EXTENSION__()
        : f => f;

    store = createStore(
      enableBatching(reducers),
      initialState,
      compose(devtools)
    );
  }

  store.dispatch(setLoggerState(logger));
  store.dispatch(setTrackingState(tracking));

  return store;
};

class Miyazaki extends App {
  static async getInitialProps({ Component, ctx }) {
    const pageProps = Component.getInitialProps
      ? await Component.getInitialProps(ctx)
      : {};

    return { pageProps };
  }

  componentDidMount() {
    window.heartbeat = heartbeat;

    const { store } = this.props;

    const initialPage = getCurrentPage(window.location.href.toString());
    store.dispatch(setCurrentPage(initialPage));
    store.dispatch(setScreenDimensions(getScreenDimensions(window.screen)));

    Router.events.on('routeChangeStart', url => {
      const currentPage = getCurrentPage(url);
      store.dispatch(setCurrentPage(currentPage));

      store.dispatch(
        setScrollPosition({
          pathname: Router.route,
          x: window.scrollX,
          y: window.scrollY
        })
      );
    });
  }

  render() {
    const { Component, pageProps, store } = this.props;
    return (
      <Container>
        <title />
        <CookiesProvider>
          <Provider store={store}>
            <React.Fragment>
              <Sidebar />
              <TopPanel />
              <TabMenu />
              <PageTransition
                timeout={400}
                classNames="page-transition"
                loadingDelay={0}
                loadingClassNames="loading-indicator"
                loadingComponent={<HooqLoader />}
                loadingTimeout={0}
              >
                <Component {...pageProps} />
              </PageTransition>

              <ToastNotification />
              <ErrorModal />
              <CustomNoSSR>
                <Drawer>
                  <LinkTV />
                </Drawer>
                <CommonNotification />
              </CustomNoSSR>
            </React.Fragment>
          </Provider>
        </CookiesProvider>
      </Container>
    );
  }
}

export default withRedux(makeStore, { debug: false })(Miyazaki);
