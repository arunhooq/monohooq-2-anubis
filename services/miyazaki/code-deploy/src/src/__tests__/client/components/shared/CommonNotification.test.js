/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';
import { Notification } from 'react-notification';
import CommonNotification from '../../../../client/components/shared/CommonNotification';

const mockStore = configureStore();

describe('<CommonNotification />', () => {
  it('should render CommonNotification', () => {
    const mockedStore = {
      pages: {
        commonNotification: {
          show: false,
          message: 'Connected to'
        }
      }
    };

    const store = mockStore(mockedStore);
    const context = { store };

    const wrapper = shallow(<CommonNotification />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find(Notification).exists()).toEqual(true);
    expect(wrapper.find('.notification-copy-inactive').exists()).toEqual(true);
  });
});
