import { logCustomEvent, logError } from './../client/helpers/newrelicUtil';

/** New Relic Transport */
export const nr = require('pino')({
  name: 'HOOQ - webview',
  browser: {
    transmit: {
      // the minimum level(inclusive) of when the send function should be called
      level: 'trace',
      send: function(level, logEvent) {
        if (level === 'trace') {
          logEvent.messages.forEach(m =>
            logCustomEvent(m.event, { type: m.type })
          );
        }

        if (logEvent.level.value >= 50) {
          logEvent.messages.forEach(m => logError(m));
        }
      }
    }
  }
});

const transports = [];
const logger = {
  getTransports: () => transports,
  /**
   * Fuse the transport to existing collection
   * @param {object} t - A transport
   * @returns {array} An updated of transport collection
   */
  fuse: t => {
    transports.push(t);
    return transports;
  },
  error: payload => {
    transports.forEach(t => {
      t.error(payload);
    });
  },
  warn: payload => {
    transports.forEach(t => {
      t.warn(payload);
    });
  },
  info: payload => {
    transports.forEach(t => {
      t.info(payload);
    });
  },
  debug: payload => {
    transports.forEach(t => {
      t.debug(payload);
    });
  },
  trace: payload => {
    transports.forEach(t => {
      t.trace(payload);
    });
  }
};

export default logger;
