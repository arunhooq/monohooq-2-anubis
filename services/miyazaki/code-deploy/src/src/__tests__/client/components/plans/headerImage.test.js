/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import configureStore from 'redux-mock-store';
import HeaderImage from '../../../../client/components/plans/headerImage';

const mockStore = configureStore();

describe('<HeaderImage />', () => {
  test('should render the image', () => {
    const mockedStore = {
      partner: {
        constants: {
          staticAssetUrl: 'https://grab-static.hooq.tv'
        }
      }
    };
    const store = mockStore(mockedStore);
    const context = { store };
    const wrapper = shallow(<HeaderImage />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('img').exists()).toEqual(true);
  });
});
