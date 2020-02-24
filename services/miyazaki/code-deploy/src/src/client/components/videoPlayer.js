import React from 'react';
import { connect } from 'react-redux';
import videojs from 'video.js';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import './videoPlayer.scss';
import ConvivaTracker from './../util/conviva/ConvivaTracker';

const mapStateToProps = (state, props) => {
  return {
    channel: state.channel,
    userSession: state.user.userSession,
    logger: state.logger,
    tracking: state.tracking,
    partnerConfig: state.partner
  };
};

export class VideoPlayer extends React.Component {
  constructor(props) {
    super(props);
    const { constants } = this.props.partnerConfig;
    const { conviva } = constants;

    if (conviva.enable) {
      try {
        this.playbackTracker = new ConvivaTracker(this.props.convivaSettings);
      } catch (err) {
        this.props.logger.error(err);
      }
    }

    this.handlePlay = this.handlePlay.bind(this);
    this.handleError = this.handleError.bind(this);
  }

  componentDidUpdate(props) {
    if (this.player) {
      this.setHlsSource();
    }
  }

  componentDidMount() {
    const opts = {
      autoplay: true,
      controls: true,
      width: '100%'
    };
    this.player = videojs(this.videoNode, opts, () => {
      this.player.on('play', this.handlePlay);
      this.player.on('error', this.handleError);
      this.player.on('durationchange', e =>
        this.handleUpdateDurationTime(e, this.props)
      );
      this.player.on('waiting', e => this.handleUpdateBuffered(e, this.props));
      this.player.on('timeupdate', e =>
        this.handleUpdateCurrentTime(e, this.props)
      );
      this.player.on('volumechange', e =>
        this.handleUpdateVolume(e, this.props)
      );
      this.player.on('seeking', e => this.handleUpdateSeeking(e, this.props));

      this.player.trigger('loadstart');
      this.setHlsSource();
    });
  }

  componentWillUnmount() {
    if (this.player) {
      this.player.dispose();
    }

    this.stopTrackingLiveTV();
  }

  setHlsSource() {
    if (this.player) {
      const { hls } = this.props.channel;
      if (hls === '') {
        return;
      }
      this.player.src(hls);
    }
  }

  handleError(e) {
    if (this.playbackTracker) {
      this.playbackTracker.logError(e);
    }

    const { channelId, hls } = this.props.channel;
    this.props.tracking.trackGtm('notification_errormessage', {
      id: channelId,
      playback_url: hls,
      source_event_name: `${this.props.page}_page`
    });
    this.props.tracking.trackGtm('message_display_playervideoerror', {
      id: channelId,
      playback_url: hls,
      source_event_name: `${this.props.page}_page`
    });
  }

  handlePlay(e) {
    if (this.videoNode && this.playbackTracker) {
      let metadata = {
        user: this.playbackTracker.createUserSessionMetadata(
          this.props.userSession
        ),
        stream: this.playbackTracker.createLiveTVMetadata(this.props.stream)
      };

      this.startTrackingLiveTV({
        metadata,
        videoNode: this.videoNode
      });
    }
  }

  handleUpdateDurationTime(e, props) {}

  handleUpdateBuffered(e, props) {}

  handleUpdateCurrentTime(e, props) {}

  handleUpdateVolume(e, props) {}

  handleUpdateSeeking(e, props) {}

  startTrackingLiveTV({ metadata, videoNode }) {
    if (this.playbackTracker) {
      this.sessionTrackingKey = this.playbackTracker.createSession(
        this.playbackTracker.createMetadata(metadata)
      );
      this.playbackTracker.attach(videoNode);
    }
  }

  stopTrackingLiveTV() {
    if (this.playbackTracker) {
      this.playbackTracker.cleanUpSession();
    }
  }

  render() {
    const { isLoading, userSession } = this.props;
    const { channelId, hls } = this.props.channel;
    const payload = { id: channelId, playback_url: hls };

    return (
      <React.Fragment>
        {isLoading && (
          <PageLoadTracker
            trackingTag="player"
            payload={payload}
            userSession={userSession}
          />
        )}
        <div className="videojs-wrapper">
          <div data-vjs-player>
            <video
              playsInline
              webkit-playsinline="true"
              data-setup='{"fluid": true}'
              id="player"
              ref={node => (this.videoNode = node)}
              className="video-js vjs-default-skin vjs-big-play-centered vjs-16-9"
            />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(VideoPlayer);
