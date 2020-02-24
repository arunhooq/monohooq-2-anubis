/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';

import mockedStore from './../../../__mocks__/store';
import { Plans } from './../../../client/pages/plans';
import Header from './../../../client/components/shared/Header';
import PlanSelector from './../../../client/components/plans/planSelector';
import PlanDescription from './../../../client/components/plans/planDescription';
import PlanAction from './../../../client/components/plans/planAction';

describe('<Plans />', () => {
  it('verify the rendered element', () => {
    mockedStore.subscription.status = 'NEW_USER';
    const mockedProps = {
      plans: mockedStore.plan.plans,
      selectedPlan: mockedStore.plan.selectedPlan
    };

    const wrapper = shallow(<Plans {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find(Header).exists()).toBeTruthy();
    expect(wrapper.find(PlanSelector).exists()).toBeTruthy();
    expect(wrapper.find(PlanDescription).exists()).toBeTruthy();
    expect(wrapper.find(PlanAction).exists()).toBeTruthy();
  });
});
