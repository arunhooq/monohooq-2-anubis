const Router = require('koa-router');
const axios = require('axios');
const SetupController = require('../controllers/Setup');
const PlayController = require('../controllers/Play');
const HeartbeatController = require('../controllers/Heartbeat');
const TvController = require('../controllers/TV');
const ActivateController = require('../controllers/Activate');
const PaymentController = require('../controllers/Payment');
const SubscriptionController = require('../controllers/Subscription');
const CallbackController = require('../controllers/Callback');
const ConfigController = require('../controllers/Config');
const PlaylistController = require('../controllers/Playlist');
const AuthController = require('../controllers/Auth');
const PartnerConfigMiddleware = require('../middlewares/PartnerConfig');
const PartnerVerifierMiddleware = require('../middlewares/PartnerVerifier');
const ApiMiddleware = require('../middlewares/Api');
const DeviceMiddleware = require('../middlewares/Device');
const SessionMiddleware = require('../middlewares/Session');
const GeocheckMiddleware = require('../middlewares/Geocheck');
const DiscoverMiddleware = require('../middlewares/Discover');
const CookieMiddleware = require('../middlewares/Cookie');
const bodyparser = require('koa-bodyparser');

function routes() {
  const route = new Router();
  const cookieAuthentication = CookieMiddleware(false);
  route.use(bodyparser());

  const stdMiddlewares = [
    PartnerConfigMiddleware,
    cookieAuthentication,
    GeocheckMiddleware,
    SessionMiddleware,
    DeviceMiddleware,
    ApiMiddleware
  ];

  const setupMiddlewares = [
    PartnerConfigMiddleware,
    PartnerVerifierMiddleware,
    GeocheckMiddleware,
    SessionMiddleware,
    DeviceMiddleware,
    ApiMiddleware
  ];

  route.get('/2.0/health', async ctx => {
    ctx.res.statusCode = 200;
  });

  route.get('/2.0/health/env', async ctx => {
    const response = {
      NODE_ENV: ctx.state.env.NODE_ENV
      // ENV: ctx.state.env.ENV,
      // NODE_DEBUG: ctx.state.env.NODE_DEBUG,
      // KASHMIR_BASE_URL: ctx.state.env.KASHMIR_BASE_URL,
      // EVE_BASE_URL: ctx.state.env.EVE_BASE_URL,
      // ENABLE_NEWRELIC: ctx.state.env.ENABLE_NEWRELIC,
      // EV_WEBVIEW_BASE_URL: ctx.state.env.EV_WEBVIEW_BASE_URL
    };
    ctx.json(response);
  });

  route.get('/2.0/health/internal-services/kashmir', async ctx => {
    try {
      const result = await axios.get(
        `${ctx.state.env.KASHMIR_BASE_URL}/health`,
        { timeout: 5000 }
      );
      return ctx.json({ data: result.data }, 200);
    } catch (err) {
      throw err;
    }
  });

  route.get('/2.0/health/internal-services/user', async ctx => {
    try {
      const result = await axios.get(
        `${ctx.state.env.PLAY_BASE_URL}/health/user`
      );
      return ctx.json({ data: result.data }, 200);
    } catch (err) {
      throw err;
    }
  });

  route.get('/2.0/health/internal-services/partner', async ctx => {
    try {
      function getPartnerId(host) {
        const splittedHost = host.split('.');
        const partnerId = splittedHost[0].replace(
          /(?:-nightly)|(?:-preprod)|(?:-loadtesting)|(?:-prod)|(?:-sandbox)+/gi,
          ''
        );
        return partnerId;
      }
      const partnerID = getPartnerId(ctx.state.request.host);
      const url = `${
        ctx.state.env.KASHMIR_BASE_URL
      }/config?partner_id=${partnerID}&partner_fields=ID%2CPartnerId%2CChannelPartnerID%2CIsActive%2CHMACKeys%2CCustomConfig&app_fields=ID%2CPartnerId%2CApplicationName%2CApplicationSecret%2CIsActive`;
      const result = await axios.get(url);
      return ctx.json({ data: result.data }, 200);
    } catch (err) {
      throw err;
    }
  });

  route.get('/2.0/health/internal-services/eve', async ctx => {
    try {
      const result = await axios.get(`${ctx.state.env.EVE_BASE_URL}/health`);
      return ctx.json({ data: result.data }, 200);
    } catch (err) {
      throw err;
    }
  });

  route.get('/setup', ...setupMiddlewares, SetupController.setup);

  route.get('/setup/:redirectPage', ...setupMiddlewares, SetupController.setup);

  route.get(
    '/play/:titleId',
    ...stdMiddlewares,
    DiscoverMiddleware,
    PlayController.getPlay
  );

  route.put(
    '/play/:titleId',
    ...stdMiddlewares,
    DiscoverMiddleware,
    HeartbeatController.putPlay
  );

  route.post('/activate', ...stdMiddlewares, ActivateController.activateUser);

  route.get('/tv/play/:channelId', ...stdMiddlewares, TvController.getTvPlay);

  route.get(
    '/payment/:redirectId',
    ...stdMiddlewares,
    PaymentController.getPaymentStatus
  );

  route.get(
    '/subscription/status',
    ...stdMiddlewares,
    SubscriptionController.getStatus
  );

  route.post(
    '/subscription/deactivate',
    ...stdMiddlewares,
    SubscriptionController.cancelSubscription
  );

  route.post(
    '/config/plans',
    ...stdMiddlewares,
    ConfigController.getPlansConfig
  );

  route.get(
    '/callback/:redirectId/:status',
    ...stdMiddlewares,
    CallbackController.callback
  );

  route.post(
    '/config/detail',
    ...stdMiddlewares,
    ConfigController.getDetailConfig
  );

  route.get(
    '/playlist/history',
    ...stdMiddlewares,
    PlaylistController.getHistory
  );

  route.get(
    '/playlist/watched',
    ...stdMiddlewares,
    PlaylistController.getRecentPlay
  );

  route.get(
    '/ev/signin',
    PartnerConfigMiddleware,
    GeocheckMiddleware,
    DeviceMiddleware,
    ApiMiddleware,
    AuthController.EvSignin
  );

  route.get(
    '/ev/auth',
    PartnerConfigMiddleware,
    GeocheckMiddleware,
    DeviceMiddleware,
    ApiMiddleware,
    SetupController.verifyEvergent
  );

  route.get('/logout', ...stdMiddlewares, AuthController.logout);

  return route;
}

module.exports = routes;
