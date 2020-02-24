const withCSS = require('@zeit/next-css');
const withSass = require('@zeit/next-sass');
const path = require('path');
const withTM = require('next-plugin-transpile-modules');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');

// next.config.js
const configs = withTM(
  withCSS(
    withSass({
      transpileModules: ['react-bulma-components', 'react-slick'],
      cssModules: false,
      webpack(config, options) {
        config.module.rules.push({
          test: /\.(eot|woff|woff2|ttf|svg|png|jpg|gif)$/,
          use: {
            loader: 'url-loader',
            options: {
              limit: 100000,
              name: '[name].[ext]'
            }
          }
        });
        config.resolve.alias = Object.assign(config.resolve.alias, {
          '_variables.sass': path.resolve(__dirname, 'styles/_variables.scss')
        });

        if (config.mode === 'production') {
          if (Array.isArray(config.optimization.minimizer)) {
            config.optimization.minimizer.push(new OptimizeCSSAssetsPlugin({}));
          }
        }

        return config;
      }
    })
  )
);

configs.publicRuntimeConfig = {
  NODE_ENV: process.env.NODE_ENV,
  ATTEMPT_TO_SHOW: process.env.ATTEMPT_TO_SHOW,
  INTERVAL_TO_SHOW: process.env.INTERVAL_TO_SHOW,
  ENABLE_NEWRELIC: process.env.ENABLE_NEWRELIC,
  NEWRELIC_BROWSER_APPLICATION_ID: process.env.NEWRELIC_BROWSER_APPLICATION_ID,
  NEWRELIC_BROWSER_LICENSE_KEY: process.env.NEWRELIC_BROWSER_LICENSE_KEY,
  NEWRELIC_BROWSER_FILE_PATH: process.env.NEWRELIC_BROWSER_FILE_PATH,
  CONVIVA_CORE_SDK_PATH: process.env.CONVIVA_CORE_SDK_PATH,
  CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH:
    process.env.CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH,
  DISCOVER_BASE_URL: process.env.DISCOVER_BASE_URL,
  CHANNEL_BASE_URL: process.env.CHANNEL_BASE_URL,
  PLAY_BASE_URL: process.env.PLAY_BASE_URL,
  SEARCH_BASE_URL: process.env.SEARCH_BASE_URL,
  EV_WEBVIEW_BASE_URL: process.env.EV_WEBVIEW_BASE_URL,
  STAGING_TITLE_ID: process.env.STAGING_TITLE_ID,
  STAGING_DISCOVER_BASE_URL: process.env.STAGING_DISCOVER_BASE_URL,
  npm_package_version: process.env.npm_package_version
};

module.exports = configs;
