import getConfig from 'next/config';

const { publicRuntimeConfig } = getConfig();

export default class ConvivaTracker {
  constructor(settings) {
    if (typeof Conviva === 'undefined') {
      throw new Error(
        'No Conviva instance found. Cannot initialize playback tracking.'
      );
    }

    this.settings = settings;

    this._initSystemSettings();
    this._initClientSettings();
  }

  isLoggingEnabled() {
    return publicRuntimeConfig.ENV !== 'production' && this.settings.enableLog;
  }

  _initSystemSettings() {
    this.systemSettings = new Conviva.SystemSettings();

    if (this.isLoggingEnabled()) {
      this.systemSettings.logLevel = Conviva.SystemSettings.LogLevel.DEBUG;
    }

    this.systemInterface = new Conviva.SystemInterface(
      new Conviva.Impl.Html5Time(),
      new Conviva.Impl.Html5Timer(),
      new Conviva.Impl.Html5Http(),
      new Conviva.Impl.Html5Storage(),
      new Conviva.Impl.Html5Metadata(),
      new Conviva.Impl.Html5Logging()
    );

    this.systemFactory = new Conviva.SystemFactory(
      this.systemInterface,
      this.systemSettings
    );

    this.sessionTrackingKey = Conviva.Client.NO_SESSION_KEY;
  }

  _initClientSettings() {
    this.clientSettings = new Conviva.ClientSettings(this.settings.customerKey);

    if (publicRuntimeConfig.ENV !== 'production') {
      this.clientSettings.gatewayUrl = this.settings.gatewayUrl;
    }

    this.client = new Conviva.Client(this.clientSettings, this.systemFactory);
  }

  createMetadata(data) {
    const { stream, user } = data;
    const metadata = new Conviva.ContentMetadata();
    metadata.assetName = stream.assetName;
    metadata.streamUrl = stream.streamUrl;
    metadata.streamType = stream.streamType;
    metadata.applicationName = this.settings.appName;
    metadata.viewerId = user.viewerId;
    metadata.custom = { device: user.device };

    return metadata;
  }

  createSession(metadata) {
    this.sessionTrackingKey = this.client.createSession(metadata);
    return this.sessionTrackingKey;
  }

  attach(videoElement) {
    if (!videoElement) {
      throw new Error('no video element supplied to attach');
    }

    if (!this.client.isPlayerAttached(this.sessionTrackingKey)) {
      this.playerStateManager = this.client.getPlayerStateManager();
      this.html5PlayerInterface = new Conviva.Impl.Html5PlayerInterface(
        this.playerStateManager,
        videoElement,
        this.systemFactory
      );
      this.client.attachPlayer(
        this.sessionTrackingKey,
        this.playerStateManager
      );
    } else {
      throw new Error('Unable to attach videoElement to tracking session');
    }
  }

  /**
   *
   * Detach the player, release the player state manager instance, and clean up session
   */
  cleanUpSession() {
    if (this.sessionTrackingKey === Conviva.Client.NO_SESSION_KEY) {
      return;
    }

    if (this.client && this.client.isPlayerAttached(this.sessionTrackingKey)) {
      this.client.detachPlayer(this.sessionTrackingKey);
    }
    if (this.html5PlayerInterface) {
      this.html5PlayerInterface.cleanup();
      this.html5PlayerInterface = null;
    }
    if (this.client) {
      this.client.releasePlayerStateManager(this.playerStateManager);
      this.client.cleanupSession(this.sessionTrackingKey);
      this.client.release();
      this.client = null;
    }
    if (this.playerStateManager) {
      this.playerStateManager.reset();
      this.playerStateManager = null;
    }

    if (this.systemFactory) {
      this.systemFactory.release();
      this.systemFactory = null;
    }
  }

  createUserSessionMetadata(userSession) {
    return {
      device: userSession.device,
      ...userSession.user,
      viewerId: userSession.user.spAccountId
    };
  }

  createLiveTVMetadata(liveTV) {
    const { playback_url, is_premium, title } = liveTV;
    return {
      streamType: Conviva.ContentMetadata.StreamType.LIVE,
      streamUrl: playback_url,
      isPremium: is_premium,
      assetName: title
    };
  }

  logError(err) {
    this.client.reportError(
      err.target.player.error_.message,
      Conviva.Client.ErrorSeverity.FATAL
    );
  }
}
