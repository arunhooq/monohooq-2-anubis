/* eslint-env jest */

import React from 'react';
import { shallow } from 'enzyme';

import ChannelPoster, {
  calculateBackgroundPosterStyle,
  calculateChannelPosterContainerClassName
} from './../../../../client/components/shared/ChannelPoster';
import { USER_LEVELS } from './../../../../client/constants/common';
import { spyOnConsoleError } from './../../../test-utils';

spyOnConsoleError();

const sampleChannel = {
  id: '0dc0ef2b-8e8d-44ba-b672-9135720389ec',
  images: {
    backgroundUrl:
      'https://commodusprod.hooq.tv/cdnimagesprod/assets/feed/56a3c200dbbd.jpg',
    logoUrl: 'https://assets.hooq.tv/feed/286bc82329ee.png',
    transparentLogoUrl:
      'https://commodusprod.hooq.tv/cdnimagesprod/assets/feed/32f3c09172be.png'
  },
  isPremium: true,
  title: 'Hits'
};

describe('<ChannelPoster />', () => {
  test('calculateBackgroundPosterStyle()', () => {
    const testCases = [
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          isChannelPremium: false
        },
        expectedResult: {
          borderRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          isChannelPremium: true
        },
        expectedResult: {
          borderRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          isChannelPremium: false
        },
        expectedResult: {
          borderTopLeftRadius: '4px',
          borderTopRightRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          isChannelPremium: true
        },
        expectedResult: {
          borderTopLeftRadius: '4px',
          borderTopRightRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          isChannelPremium: false
        },
        expectedResult: {
          borderTopLeftRadius: '4px',
          borderTopRightRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          isChannelPremium: true
        },
        expectedResult: {
          borderTopLeftRadius: '4px',
          borderTopRightRadius: '4px',
          height: 'auto',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        }
      }
    ];

    testCases.map(testCase => {
      const result = calculateBackgroundPosterStyle(
        testCase.value.userLevel,
        testCase.value.isChannelPremium
      );
      expect(result).toEqual(testCase.expectedResult);
    });
  });

  test('calculateChannelPosterContainerClassName()', () => {
    const testCases = [
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          isChannelPremium: false
        },
        expectedResult: 'channel-poster-container--all-rounded'
      },
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          isChannelPremium: true
        },
        expectedResult: 'channel-poster-container--all-rounded'
      },
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          isChannelPremium: false
        },
        expectedResult: 'channel-poster-container--top-rounded'
      },
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          isChannelPremium: true
        },
        expectedResult: 'channel-poster-container--top-rounded'
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          isChannelPremium: false
        },
        expectedResult: 'channel-poster-container--top-rounded'
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          isChannelPremium: true
        },
        expectedResult: 'channel-poster-container--top-rounded'
      }
    ];

    testCases.map(testCase => {
      const result = calculateChannelPosterContainerClassName(
        testCase.value.userLevel,
        testCase.value.isChannelPremium
      );
      expect(result).toEqual(testCase.expectedResult);
    });
  });

  test('<ChannelPoster /> with valid props should be rendered successfully', () => {
    const wrapper = shallow(
      <ChannelPoster channel={sampleChannel} userLevel={10} />
    );
    expect(wrapper.isEmptyRender()).toBe(false);
  });

  test('`channel` prop should be required in <ChannelPoster />)', () => {
    expect(() => {
      shallow(<ChannelPoster />);
    }).toThrowError(/The prop `channel` is marked as required/);
  });

  test('`userLevel` prop should be required in <ChannelPoster />)', () => {
    expect(() => {
      shallow(<ChannelPoster channel={sampleChannel} />);
    }).toThrowError(/The prop `userLevel` is marked as required/);
  });
});
