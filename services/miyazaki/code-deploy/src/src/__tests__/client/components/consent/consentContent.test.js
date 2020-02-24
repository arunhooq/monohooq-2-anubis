/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';

import mockedStore from './../../../../__mocks__/store';
import { ConsentContent } from './../../../../client/components/consent/consentContent';

describe('<ConsentContent />', () => {
  test('if the subscription status is NEW_USER,', () => {
    mockedStore.subscription.status = 'NEW_USER';
    const mockedProps = {
      consent: mockedStore.detail.consent,
      translation: mockedStore.detail.translation,
      region: mockedStore.discover.region,
      subscription: mockedStore.subscription,
      userSession: mockedStore.user.userSession
    };

    const wrapper = shallow(<ConsentContent {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('#checkboxterms').exists()).toBeTruthy();
    expect(wrapper.find('#checkboxpolicy').exists()).toBeTruthy();
  });
});
