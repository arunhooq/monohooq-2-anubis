if (process.env.ENABLE_NEWRELIC === 'true') {
  require('newrelic');
}

const next = require('next');
const http = require('http');
const { bootstrap } = require('./bootstrap');

const NODE_PORT = process.env.NODE_PORT;
const dev = process.env.NODE_ENV !== 'production';
const app = next({ dir: './src/client', dev });
const handle = app.getRequestHandler();

app.prepare().then(() => {
  const koa = bootstrap(app, handle);

  const server = http.createServer(koa.callback());
  server.listen(NODE_PORT || 4352, err => {
    if (err) {
      console.log(`Failed to start the server: ${err}`);
    }
    console.log(`NODE_ENV: ${process.env.NODE_ENV}`);
    console.log(`Server running at port: ${server.address().port}`);
  }); 
});
