import { shallow } from 'enzyme';
import Slider from 'react-slick';
import rowMock from '../../../mocks/rowChannels';

import { Channel } from '../../../../../src/client/components/discover/channels';

describe('test Channels rail component', () => {
  it('should render Channel rail', () => {
    const mockProps = {
      row: rowMock,
      userLevel: 20
    };
    const wrapper = shallow(<Channel {...mockProps} />, {
      disableLifecycleMethods: true
    });
    expect(wrapper.find('.channel-title-medium')).toHaveLength(1);
    expect(wrapper.find('.channels-section')).toHaveLength(1);
  });

  it('should render Slider', () => {
    const mockProps = {
      row: rowMock,
      userLevel: 20
    };
    const wrapper = shallow(<Channel {...mockProps} />, {
      disableLifecycleMethods: true
    });
    expect(wrapper.exists(Slider)).toBe(true);
  });
});
