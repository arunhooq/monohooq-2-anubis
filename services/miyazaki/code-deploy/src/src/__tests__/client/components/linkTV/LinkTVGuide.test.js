/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import LinkTVGuide from '../../../../client/components/linkTV/LinkTVGuide';

describe('<LinkTVGuide />', () => {
  it('should render LinkTVGuide', () => {
    const wrapper = shallow(<LinkTVGuide />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('.link-tv__guide').exists()).toEqual(true);
    expect(wrapper.find('.link-tv__guide > p').exists()).toEqual(true);
    expect(wrapper.find('.link-tv__guide > span').exists()).toEqual(true);
  });
});
