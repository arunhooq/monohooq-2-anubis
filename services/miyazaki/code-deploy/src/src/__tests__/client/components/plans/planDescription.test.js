/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';

import PlanDescription, {
  PlanBenefits
} from './../../../../client/components/plans/planDescription';

describe('<PlanDescription />', () => {
  it('should render component', () => {
    const mockedProps = {
      plan: {
        benefits: [],
        currency: '',
        duration: 0,
        name: '',
        paymentUrl: '',
        price: 24000
      }
    };

    const wrapper = shallow(<PlanDescription {...mockedProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('.plan-description')).toBeTruthy();
  });

  it('non-empty benefits should render plan benefit items', () => {
    const mockedProps = {
      plan: {
        benefits: ['a', 'b', 'c'],
        currency: 'IDR',
        duration: 90,
        name: 'Plan 3 bulan',
        paymentUrl: '',
        price: 100000
      }
    };

    const wrapper = shallow(
      <PlanBenefits benefits={mockedProps.plan.benefits} />
    );

    expect(wrapper.find('.plan-description-item > li')).toHaveLength(
      mockedProps.plan.benefits.length
    );
  });
});
