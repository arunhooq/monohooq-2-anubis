/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';

import {
  RowContinueWatch,
  CollectionTitle
} from '../../../../client/components/discover/rowDisplay';

import { movies } from '../../../mocks/historyWatch';

describe(`<RowContinueWatch />`, () => {
  it('should render the posters of continue watch', () => {
    const mockProps = { movies };

    const wrapper = shallow(<RowContinueWatch {...mockProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.exists('div.continue-watch-rail')).toBe(true);
    expect(wrapper.find(CollectionTitle)).toHaveLength(1);
  });

  it('should not show any continue watch poster', () => {
    const mockProps = { movies: [] };

    const wrapper = shallow(<RowContinueWatch {...mockProps} />, {
      disableLifecycleMethods: true
    });

    expect(wrapper.exists('div.continue-watch-rail .is-4')).toBe(false);
    expect(wrapper.find(CollectionTitle)).toEqual({});
  });
});
