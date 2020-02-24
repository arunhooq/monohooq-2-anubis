export default class DevicePairingService {
  constructor(serviceUrl, callback) {
    this.socket = new WebSocket(serviceUrl);
    this.callback = callback;

    this.socket.onmessage = e => {
      const { data } = e;
      const parsedData = JSON.parse(data);
      if (parsedData.event === 'key-validation') {
        if (parsedData.data.match) {
          this.callback(null, parsedData.data);
        }
        if (parsedData.data.match === false) {
          this.callback(new Error(parsedData.data.error));
        }
      }
    };

    this.socket.onclose = e => {
      if (e.wasClean === false) {
        // TODO: log to NR
        console.log(
          `[close] Connection closed cleanly, code=${e.code} reason=${e.reason}`
        );
      }
    };

    this.socket.onerror = err => {
      // TODO: log to NR
      this.callback(err);
    };
  }

  validateKey({ credentials, key }) {
    this.socket.send(
      JSON.stringify({
        action: 'validate-key',
        credentials,
        key
      })
    );
  }
}
