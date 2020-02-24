import { shallow } from 'enzyme';

import { row } from '../../../../__mocks__/spotlight';
import RowTitle from '../../../../../src/client/components/shared/RowTitle';

describe('test RowTitle components', () => {
  it('should render RowTitle', () => {
    const mockProps = {
      row: row,
      translation: {
        curation_seeAll: ''
      }
    };
    const wrapper = shallow(<RowTitle {...mockProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.find('.row-title')).toHaveLength(1);
    expect(wrapper.find('.row-title__title')).toHaveLength(1);
    expect(wrapper.find('.row-title__text')).toHaveLength(1);
    expect(wrapper.find('.row-title__see-all')).toHaveLength(1);
  });
});
