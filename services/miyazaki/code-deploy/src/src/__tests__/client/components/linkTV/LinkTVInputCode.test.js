/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';
import LinkTVInputCode from '../../../../client/components/linkTV/LinkTVInputCode';

const mockStore = configureStore();

describe('<LinkTVInputCode />', () => {
  it('should render LinkTVInputCode', () => {
    const mockedStore = {
      translation: {
        link_btn: 'Link now'
      },
      partner: {
        constants: {
          devicePairingServiceUrl:
            'wss://pair-nightly.hooq.tv/1.1-alpha/device-pairing'
        }
      }
    };

    const store = mockStore(mockedStore);
    const context = { store };

    const wrapper = shallow(<LinkTVInputCode />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('.link-tv__input_wrapper').exists()).toEqual(true);
    expect(wrapper.find('.link-tv__input').exists()).toEqual(true);
  });
});
