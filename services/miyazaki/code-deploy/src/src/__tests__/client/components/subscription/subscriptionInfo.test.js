/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import configureStore from 'redux-mock-store';
import mockedStore from './../../../../__mocks__/store';
import SubscriptionInfo from './../../../../client/components/subscription/subscriptionInfo';
import Translation from './../../../../__mocks__/translation';

const mockStore = configureStore();

describe('<SubscriptionInfo />', () => {
  test('if the subscription status is FREE TRIAL,', () => {
    const mockedProps = {
      translation: Translation,
      user: mockedStore.user.userSession
    };

    const store = mockStore(mockedProps);
    const context = { store };

    let wrapper = shallow(<SubscriptionInfo />, {
      context,
      disableLifecycleMethods: false
    });

    wrapper.render();
  });
});
