/* eslint-env jest */

import { shallow } from 'enzyme';
import sinon from 'sinon';
import React from 'react';

import PlanSelector, {
  Selector
} from './../../../../client/components/plans/planSelector';

describe('<PlanSelector />', () => {
  it('should render no plan item if the props.plans is an empty array', () => {
    const mockedProps = { plans: [] };

    const wrapper = shallow(<PlanSelector {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.exists('nav.plan-selector.level.is-mobile')).toBe(true);
    expect(wrapper.find(Selector)).toHaveLength(0);
  });

  it('should render plan item if the props.plans is not empty', () => {
    const mockedProps = {
      plans: [
        {
          benefits: [],
          currency: '',
          duration: 0,
          name: '',
          paymentUrl: '',
          price: 24000
        }
      ]
    };

    const wrapper = shallow(<PlanSelector {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.exists('nav.plan-selector.level.is-mobile')).toBe(true);
    expect(wrapper.find(Selector)).toHaveLength(1);
  });

  it('simulates click events on the plan selector', () => {
    const mockedProps = {
      plans: [
        {
          benefits: [],
          currency: '',
          duration: 0,
          name: '',
          paymentUrl: '',
          price: 24000
        }
      ]
    };

    const onSelectedPlan = sinon.spy();
    const wrapper = shallow(
      <PlanSelector {...mockedProps} onSelectedPLan={onSelectedPlan} />,
      {
        disableLifecycleMethods: true
      }
    );

    wrapper.find('div.is-marginless.level-item.active').simulate('click');
    expect(onSelectedPlan).toHaveProperty('callCount', 1);
  });
});
