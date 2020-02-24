/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';

const mockStore = configureStore();
import ToastNotification from '../../../../client/components/shared/ToastNotification';

describe('<ToastNotification />', () => {
  it('should render ToastNotification', () => {
    const mockedStore = {
      pages: {
        toastNotification: {
          open: true,
          title: 'Title',
          subtitle: 'Subtitle'
        }
      }
    };

    const store = mockStore(mockedStore);
    const context = { store };

    const wrapper = shallow(<ToastNotification />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('.toast').exists()).toEqual(true);
    expect(wrapper.find('.toast__wrapper').exists()).toEqual(true);
    expect(wrapper.find('.toast__image').exists()).toEqual(true);
    expect(wrapper.find('.toast__content').exists()).toEqual(true);
    expect(wrapper.find('.toast__title').exists()).toEqual(true);
  });
});
