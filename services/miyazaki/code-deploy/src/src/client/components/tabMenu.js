import React, { Component } from 'react';
import { Router } from '../routes';
import { connect } from 'react-redux';
import { get } from 'lodash';
import { Tabs } from 'react-bulma-components';
import { setMenuTab } from '../actions/menuAction';
import { setChannels } from '../actions/channelAction';
import { getFirstChannel, setInitialChannels } from '../util/channel';
import { DISCOVER, MOVIES, TVSHOWS, CHANNEL } from '../constants/tabMenu';
import './tabMenu.scss';

const mapStateToProps = state => ({
  menu: state.menu,
  channel: state.channel,
  discover: state.discover,
  partnerConfig: state.partner,
  translation: state.translation
});

const mapDispatchToProps = dispatch => ({
  setMenuTab: menu => dispatch(setMenuTab(menu)),
  setChannels: channels => dispatch(setChannels(channels))
});

class TabMenu extends Component {
  redirect = async option => {
    let params = {};

    if (option.href === 'channel') {
      const { region } = this.props.discover;
      const { channels } = this.props.channel;
      await setInitialChannels(
        region,
        channels,
        this.props.setChannels.bind(this)
      );

      const channelId = this.getFirstChannelId();
      params = Object.assign(params, { channelId });
    }
    if (option.valid) {
      this.props.setMenuTab(option.href);
    }

    Router.pushRoute(option.href, params);
  };

  getFirstChannelId() {
    const { channels } = this.props.channel;
    const channel = getFirstChannel(channels);
    return channel.id;
  }

  render() {
    const { translation, partnerConfig } = this.props;
    const { tab, visible } = this.props.menu;

    let options = [
      { label: translation.mainMenu_discover, href: DISCOVER, valid: true },
      { label: translation.mainMenu_movies, href: MOVIES, valid: true },
      { label: translation.mainMenu_tvShows, href: TVSHOWS, valid: true }
    ];

    let channelNav = { label: 'Channels', href: CHANNEL, valid: true };

    if (get(partnerConfig, 'restriction.liveTv.enable')) {
      options.splice(1, 0, channelNav); // Adds to second index
      options.join();
    }

    return (
      <React.Fragment>
        {visible && (
          <div className="tab-wrapper">
            <Tabs className="is-centered is-small is-fullwidth">
              {options.map((option, index) => {
                return (
                  <React.Fragment key={index}>
                    <li
                      key={index}
                      onClick={() => this.redirect(option)}
                      className={
                        option.href === tab
                          ? 'item-option is-active'
                          : 'item-option'
                      }
                    >
                      <a
                        className={
                          option.href === tab
                            ? 'tab-option is-active'
                            : 'tab-option'
                        }
                      >
                        {option.label}
                      </a>
                    </li>
                  </React.Fragment>
                );
              })}
            </Tabs>
          </div>
        )}
        {visible && <div style={{ marginTop: '6rem' }} />}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TabMenu);
