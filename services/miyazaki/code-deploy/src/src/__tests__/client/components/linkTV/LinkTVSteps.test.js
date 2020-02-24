/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import LinkTVSteps from '../../../../client/components/linkTV/LinkTVSteps';

describe('<LinkTVSteps />', () => {
  it('should render LinkTVSteps', () => {
    const mockProps = {
      translation: {
        linkTv_steps:
          'Launch HOOQ on your smart TV device. ,\n\nSelect <b>Log In</b>. ,\n\nYour TV code appears on the screen.',
        linkTv_steps_closeButton: 'Close'
      },
      setVisible: () => {}
    };
    const wrapper = shallow(<LinkTVSteps {...mockProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('.link-tv__steps').exists()).toEqual(true);
    expect(wrapper.find('.link-tv__steps > ol > li').length > 0).toEqual(true);
    expect(wrapper.find('.link-tv__steps__button').exists()).toEqual(true);
  });
});
