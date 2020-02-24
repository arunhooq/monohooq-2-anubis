/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import { RelatedMovies } from '../../../../client/components/detail/relatedMovies';
import seasonDetailMock from '../../../../__mocks__/seasonDetail';

describe('<RelatedMovies />', () => {
  const props = seasonDetailMock;
  const wrapper = shallow(<RelatedMovies {...props} />);
  test('should have "related-movie__cards" css class', () => {
    expect(wrapper.find('.related-movie__cards').exists()).toEqual(true);
    expect(wrapper.find('.poster-wrapper')).toHaveLength(9);
  });
});
