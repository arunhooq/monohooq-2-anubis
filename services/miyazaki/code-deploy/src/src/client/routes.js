const routes = require('next-routes');

module.exports = routes()
  .add('discover', '/', 'index')
  .add('health', '/health', 'health')
  .add('movies', '/movies', 'movies')
  .add('tvshows', '/tvshows', 'tvshows')
  .add('search', '/search', 'search')
  .add('subscription', '/subscription', 'subscription')
  .add('relatedSearch', '/related', 'relatedSearch')
  .add('detail', '/detail/:movieId', 'detail')
  .add('channel', '/channel/:channelId?', 'channel')
  .add('terms', '/terms', 'terms')
  .add('plans', '/plans', 'plans')
  .add('policy', '/policy/:company', 'policy')
  .add('collection', '/discover/:collectionId', 'collection')
  .add('consent', '/consent', 'consent')
  .add('_error', '/error', '_error');
