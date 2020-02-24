import React from 'react';
import Slider from 'react-slick';
import { connect } from 'react-redux';
import { batchActions } from 'redux-batched-actions';
import { Router } from '../../routes';
import { setMenuTab } from '../../actions/menuAction';
import { setChannelId, setHls } from '../../actions/channelAction';
import { CHANNEL } from '../../constants/tabMenu';
import { redirectErrorPage } from '../../helpers/redirect';
import { getManifest } from '../../services/channels';
import { createPoster } from './../shared/ChannelPoster';
import './channels.scss';
import 'slick-carousel/slick/slick.scss';
import 'slick-carousel/slick/slick-theme.scss';

const mapStateToProps = (state, ownProps) => {
  return {
    discover: state.discover,
    tracking: state.tracking,
    userLevel: state.user.userSession.user.userLevel
  };
};

const mapDispatchToProps = dispatch => ({
  batchActions: actions => dispatch(batchActions(actions)),
  setMenuTab: tab => dispatch(setMenuTab(tab))
});

const smallScreen = {
  slidesToShow: 4,
  slidesToScroll: 4
};

const xsmallScreen = {
  slidesToShow: 3,
  slidesToScroll: 3
};

const bigScreen = {
  slidesToShow: 7,
  slidesToScroll: 7
};

const settings = {
  dots: false,
  infinite: false,
  arrows: false,
  responsive: [
    {
      breakpoint: 1040,
      settings: bigScreen
    },
    {
      breakpoint: 1024,
      settings: bigScreen
    },
    {
      breakpoint: 600,
      settings: smallScreen
    },
    {
      breakpoint: 480,
      settings: smallScreen
    },
    {
      breakpoint: 360,
      settings: xsmallScreen
    }
  ]
};

export class Channel extends React.Component {
  handleChannelQuicklinkClick({ id, isPremium, title }) {
    this.props.tracking.trackGtm('button_tap_channel_discover', {
      id,
      isPremium,
      title,
      source_event_name: `${this.props.page}_page`
    });

    Router.pushRoute('channel', { channelId: id });
  }

  async handleSelectChannel(channel) {
    channel.id = channel.id.toUpperCase();

    try {
      const stream = await getManifest({ channelId: channel.id });
      this.props.batchActions([
        setChannelId(channel.id),
        setHls(stream.playback_url)
      ]);

      this.props.setMenuTab(CHANNEL);
      this.handleChannelQuicklinkClick(channel);
    } catch (error) {
      redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'true',
          back: 'false',
          retryUrl: '/'
        },
        error
      );
    }
  }

  render() {
    const { userLevel } = this.props;
    const tv = this.props.row;
    let channelData = tv.hasOwnProperty('data') ? tv.data : [];
    const channels = channelData.filter(channel => {
      return channel.isPremium === false;
    });

    if (channels.length === 0) {
      return null;
    }

    return (
      <React.Fragment>
        {channels.length && (
          <React.Fragment key={tv.row_id}>
            <div className="container" style={{ marginTop: '0.9rem' }}>
              <div className="channel-title-medium">{tv.row_name}</div>
            </div>
            <div className="container channels-section">
              <Slider {...settings} className="channels-list">
                {channels.map((channel, index) => {
                  const Poster = createPoster({
                    label: {
                      userLevel,
                      isPremium: false
                    }
                  });
                  return (
                    <div
                      className="channel-option"
                      key={index}
                      onClick={() => this.handleSelectChannel(channel)}
                    >
                      <Poster
                        images={channel.images}
                        title={channel.title}
                        channel={channel}
                        userLevel={userLevel}
                      />
                    </div>
                  );
                })}
              </Slider>
            </div>
          </React.Fragment>
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Channel);
