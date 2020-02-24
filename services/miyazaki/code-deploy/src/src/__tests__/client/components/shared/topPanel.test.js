/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';

const mockStore = configureStore();
import { ConnectedTopPanel } from '../../../../client/components/search/topPanel';
import IconCast from '../../../../client/components/icons/IconCast';
import IconSearch from '../../../../client/components/icons/IconSearch';
import IconSidebar from '../../../../client/components/icons/IconSidebar';

describe.skip('<ConnectedTopPanel />', () => {
  it('Should render top panel', () => {
    const mockedStore = {
      menu: {
        visible: true
      },
      search: {
        searchActive: false
      },
      discover: {
        region: 'ID'
      },
      user: {
        partner: {
          actions: {
            user: {
              primaryId: {
                type: 'phoneNumber'
              }
            }
          }
        },
        userSession: {
          isVisitor: false,
          user: {
            phoneNumber: '85212341234'
          }
        }
      }
    };

    const store = mockStore(mockedStore);
    const context = { store };

    const wrapper = shallow(<ConnectedTopPanel />, {
      context,
      disableLifecycleMethods: true
    }).dive();

    expect(wrapper.find('.navbar').exists()).toEqual(true);
    expect(wrapper.find('.navbar-brand').exists()).toEqual(true);
    expect(wrapper.find('.navbar-menu').exists()).toEqual(true);
    expect(wrapper.find('.navbar-end').exists()).toEqual(true);
    expect(wrapper.find('.menu-btn').exists()).toEqual(true);
    expect(wrapper.find(IconCast).exists()).toEqual(true);
    expect(wrapper.find(IconSearch).exists()).toEqual(true);
    expect(wrapper.find(IconSidebar).exists()).toEqual(true);
  });
});
