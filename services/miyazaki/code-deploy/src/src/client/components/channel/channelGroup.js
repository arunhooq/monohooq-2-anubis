import React from 'react';
import { connect } from 'react-redux';
import { createPoster } from './../shared/ChannelPoster';
import './channelGroup.scss';

const mapStateToProps = (state, props) => {
  return {
    channel: state.channel,
    userLevel: state.user.userSession.user.userLevel
  };
};

export class ChannelGroup extends React.Component {
  render() {
    const { userLevel, selectedChannelId, handleSelectChannel } = this.props;
    const { channels } = this.props.channel;
    return (
      <div className="container channel-section">
        {channels.map(row => {
          return (
            row.data.length > 0 && (
              <div className="channel-group-wrapper" key={row.row_id}>
                <p className="channel-group-title">{row.row_name}</p>
                <div className="columns is-multiline is-mobile is-variable is-1-mobile">
                  {row.data.map(channel => {
                    const Poster = createPoster({
                      label: {
                        userLevel,
                        isPremium: channel.isPremium
                      }
                    });

                    const channelPosterContainerClassName =
                      selectedChannelId === channel.id
                        ? 'channel-poster-container-parent--state-selected'
                        : 'channel-poster-container-parent';

                    return (
                      <div
                        className="column is-4"
                        onClick={() => handleSelectChannel(channel)}
                        key={channel.id}
                      >
                        <div className={channelPosterContainerClassName}>
                          <div className="channel-poster-container-child">
                            <Poster
                              images={channel.images}
                              title={channel.title}
                              channel={channel}
                              userLevel={userLevel}
                            />
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
            )
          );
        })}
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(ChannelGroup);
