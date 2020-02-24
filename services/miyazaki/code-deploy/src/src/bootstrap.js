const Koa = require('koa');
const compression = require('compression');
const koaConnect = require('koa-connect');
const userAgent = require('koa-useragent');
const bodyParser = require('koa-bodyparser');
const state = require('./server/Http/middlewares/State');
const json = require('./server/Http/middlewares/Json');
const redirect = require('./server/Http/middlewares/Redirect');
const errorHandler = require('./server/Http/middlewares/ErrorHandler');
const staticCache = require('./server/Http/middlewares/StaticCache');
const apiRouter = require('./server/Http/routes/api');
const webRouter = require('./server/Http/routes/web');

const bootstrap = (app, handle) => {
  const koa = new Koa();

  koa.use(koaConnect(compression()));
  koa.use(userAgent);
  koa.use(bodyParser());
  koa.use(state);
  koa.use(json);
  koa.use(redirect);
  koa.use(errorHandler);
  koa.use(staticCache);
  koa.use(apiRouter().routes());
  koa.use(webRouter(app, handle).routes());

  return koa;
};

module.exports = {
  bootstrap
};
