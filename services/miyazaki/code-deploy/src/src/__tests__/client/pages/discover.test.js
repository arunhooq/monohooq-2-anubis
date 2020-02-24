/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';

import mockedStore from './../../../__mocks__/store';
import { Discover } from './../../../client/pages/index';

describe('<Discover />', () => {
  it('verify the rendered element', () => {
    mockedStore.subscription.status = 'NEW_USER';
    const mockedProps = {
      setVisibility: () => {},
      loading: { isLoading: false },
      discover: {
        discoverRows: [],
        region: 'ID'
      },
      selectedPlan: mockedStore.plan.selectedPlan
    };

    const wrapper = shallow(<Discover {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('.section').exists()).toBeTruthy();
    expect(wrapper.find('.container').exists()).toBeTruthy();
  });
});
