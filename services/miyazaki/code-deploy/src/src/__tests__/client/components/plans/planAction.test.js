/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import configureStore from 'redux-mock-store';
import PlanAction from '../../../../client/components/plans/planAction';

const mockStore = configureStore();
const store = mockStore({});
const context = { store };

describe('<PlanAction />', () => {
  test('should render the plan-selector-footer', () => {
    const mockedProps = {
      titleId: 'titleId',
      selectedPlan: {},
      planCTALabel: 'Bayar dengan OVO',
      planCancelLabel: 'Batal'
    };

    const wrapper = shallow(<PlanAction {...mockedProps} />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('footer.plan-selector-footer').exists()).toEqual(true);
    expect(wrapper.find('button.plan-selector-btn')).toHaveLength(2);
    expect(
      wrapper
        .find('button.has-background-color-primary')
        .children()
        .text()
    ).toEqual(mockedProps.planCTALabel);
    expect(
      wrapper
        .find('button.is-white')
        .children()
        .text()
    ).toEqual(mockedProps.planCancelLabel);
  });
});
