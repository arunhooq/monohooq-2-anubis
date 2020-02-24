import { shallow } from 'enzyme';
import React from 'react';

import mockedStore from './../../../__mocks__/store';
import { Detail } from './../../../client/pages/detail';
import movieMock from './../../mocks/movieMock';
import sessionMock from './../../mocks/userSession';
import translation from '../../../__mocks__/translation';

describe('<Detail />', () => {
  it('verify the rendered element', () => {
    mockedStore.subscription.status = 'NEW_USER';
    const mockedProps = {
      translation,
      userSession: sessionMock.user,
      detail: {
        movie: movieMock.movie,
        loading: {
          isLoading: false
        },
        config: {}
      },
      loading: {
        spinner: null
      },
      setVisibility: () => {}
    };
    const wrapper = shallow(<Detail {...mockedProps} />, {
      disableLifecycleMethods: true
    });
    expect(wrapper.find('.poster-container').exists()).toBeTruthy();
    expect(wrapper.find('.poster-blur').exists()).toBeTruthy();
  });
});
