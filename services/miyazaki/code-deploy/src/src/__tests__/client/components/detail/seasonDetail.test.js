/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import { SeasonDetail } from '../../../../client/components/detail/seasonDetail';
import seasonDetailMock from '../../../../__mocks__/seasonDetail';

describe('<SeasonDetail />', () => {
  const props = seasonDetailMock;
  const wrapper = shallow(<SeasonDetail {...props} />);

  test('should have several css class', () => {
    expect(wrapper.find('.season_option_wrapper').exists()).toEqual(true);
  });

  test('should have "season_option_item" css class', () => {
    expect(wrapper.find('.season_option_item').exists()).toEqual(true);
  });

  test('should have "season_option_container" css class', () => {
    expect(wrapper.find('.season_option_container').exists()).toEqual(true);
  });

  test('should have "season_option_item_active" css class', () => {
    expect(wrapper.find('.season_option_item_active').exists()).toEqual(true);
  });

  test('should have "accordions" css class', () => {
    expect(wrapper.find('.accordions').exists()).toEqual(true);
  });

  test('should have "accordion" css class', () => {
    expect(wrapper.find('.accordion').exists()).toEqual(true);
  });

  test('should have "accordion-header" css class', () => {
    expect(wrapper.find('.accordion-header').exists()).toEqual(true);
  });

  test('should have "movie-list-box" css class', () => {
    expect(wrapper.find('.movie-list-box').exists()).toEqual(true);
  });

  test('should have "accordion-body" css class', () => {
    expect(wrapper.find('.accordion-body').exists()).toEqual(true);
  });

  test('should have "accordion-content" css class', () => {
    expect(wrapper.find('.accordion-content').exists()).toEqual(true);
  });
});
