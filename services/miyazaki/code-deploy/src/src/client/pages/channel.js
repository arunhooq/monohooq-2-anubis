import React from 'react';
import { connect } from 'react-redux';
import { batchActions } from 'redux-batched-actions';
import { Router } from '../routes';
import VideoPlayer from '../components/videoPlayer';
import ChannelGroup from '../components/channel/channelGroup';
import { setLoadingState } from '../actions/loadingAction';
import { setChannels, setChannelId, setHls } from '../actions/channelAction';
import { setScrollPosition } from '../actions/pagesAction';
import { setMenuTab, setVisibility } from '../actions/menuAction';
import { CHANNEL } from '../constants/tabMenu';
import { getManifest } from '../services/channels';
import { setInitialProps, setViewPortPosition } from '../util/pages';
import {
  setInitialChannels,
  initiateScript,
  staticAssets
} from '../util/channel';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import { getFirstChannel } from '../util/channel';
import logger from '../../common/ClientLogger';

const mapStateToProps = (state, props) => {
  return {
    channel: state.channel,
    discover: state.discover,
    loading: state.loading,
    pages: state.pages,
    convivaSettings: state.convivaSettings,
    tracking: state.tracking,
    userSession: state.user.userSession
  };
};

const mapDispatchToProps = dispatch => ({
  batchActions: actions => dispatch(batchActions(actions)),
  setMenuTab: menu => dispatch(setMenuTab(menu)),
  setLoadingState: isLoading => dispatch(setLoadingState(isLoading)),
  setChannels: channels => dispatch(setChannels(channels)),
  setVisibility: visible => dispatch(setVisibility(visible)),
  setScrollPosition: payload => dispatch(setScrollPosition(payload))
});

class Channel extends React.Component {
  static pageTransitionDelayEnter = true;

  constructor(props) {
    super(props);

    initiateScript(staticAssets);
  }

  static async getInitialProps(ctx) {
    const { asPath, pathname } = ctx;
    const { channelId, loading } = ctx.query;
    const isLoading = loading === 'false' ? false : true;
    await setInitialProps({ ...ctx, isLoading });

    return {
      channelId,
      isLoading,
      asPath,
      pathname
    };
  }

  componentWillMount() {
    this.props.setVisibility(true);
  }

  async componentDidMount() {
    document.title = 'Channels';
    this.props.setMenuTab(CHANNEL);

    this.timeoutId = setTimeout(() => {
      this.props.pageTransitionReadyToEnter();
      this.props.setLoadingState(false);

      const { loading, pages, pathname } = this.props;
      setViewPortPosition(loading.isLoading, pages, pathname);
    }, 400);

    const { region } = this.props.discover;
    const { channels } = this.props.channel;
    await setInitialChannels(
      region,
      channels,
      this.props.setChannels.bind(this)
    );

    const { channelId } = this.props;
    let selectedChannelId = channelId;

    if (selectedChannelId === undefined) {
      const channel = getFirstChannel(this.props.channel.channels);
      selectedChannelId = channel.id;
      Router.replaceRoute(`/channel/${selectedChannelId}?loading=false`);
    }

    const { isLoading, pages, pathname } = this.props;
    if (!isLoading) {
      // Restore viewport when loading is not rendered
      setViewPortPosition(isLoading, pages, pathname);
    }

    await this.fetchTVStreamData(selectedChannelId);
  }

  componentWillUnmount() {
    if (this.timeoutId) clearTimeout(this.timeoutId);
  }

  async fetchTVStreamData(channelId) {
    try {
      const stream = await getManifest({ channelId });
      const hls = stream.playback_url;
      this.currentStream = stream;

      const { channel } = this.props;
      if (channel.channelId !== channelId) {
        this.props.batchActions([setChannelId(channelId), setHls(hls)]);
      }
    } catch (err) {
      logger.error(err);
    }
  }

  async handleSelectChannel({ id, isPremium, title }) {
    this.props.tracking.trackGtm('button_tap_channel', {
      id,
      isPremium,
      title,
      source_event_name: `channel_page`
    });
    await this.fetchTVStreamData(id);

    Router.pushRoute('channel', {
      channelId: id,
      loading: false
    });
  }

  render() {
    const {
      convivaSettings,
      loading,
      userSession,
      isLoading,
      channelId
    } = this.props;
    const page = 'channel';
    if (loading.isLoading) return null;

    const payload = { id: channelId };

    return (
      <React.Fragment>
        <VideoPlayer
          convivaSettings={convivaSettings}
          stream={this.currentStream}
          isLoading={isLoading}
          page={page}
        />

        {isLoading && (
          <PageLoadTracker
            trackingTag="livetv"
            payload={payload}
            userSession={userSession}
          />
        )}
        <ChannelGroup
          selectedChannelId={channelId}
          handleSelectChannel={this.handleSelectChannel.bind(this)}
        />
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Channel);
