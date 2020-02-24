import { shallow } from 'enzyme';
import React from 'react';

import Channel from './../../../client/pages/channel';
import configureStore from 'redux-mock-store';
import { JSDOM } from 'jsdom';
import { user } from '../../mocks/userSession';
import { convivaSettings } from '../../mocks/conviva';

const dom = new JSDOM();
global.document = dom.window.document;
global.window = dom.window;

const mockStore = configureStore();
const mockProps = {
  convivaSettings,
  loading: { isLoading: false, spinner: false },
  userSession: user,
  isLoading: false,
  channelId: '192CEA0E-578E-4533-A4B1-05985627F5AB',
  user: { userSession: user }
};
const store = mockStore(mockProps);
const context = { store };

describe('<Channel />', () => {
  it('verify the rendered element', () => {
    const wrapper = shallow(<Channel />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('Connect(ChannelGroup)')).toHaveLength(1);
    expect(wrapper.find('Connect(VideoPlayer)').exists()).toBeTruthy();
  });
});
