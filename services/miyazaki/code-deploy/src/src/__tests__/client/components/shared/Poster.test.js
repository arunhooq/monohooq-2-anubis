/* eslint-env jest */

import { USER_LEVELS } from './../../../../client/constants/common';
import { calculateLabel } from './../../../../client/components/shared/Poster';

describe('calculateLabel(...)', () => {
  test('calculateLabel', () => {
    const testCases = [
      // content level 10
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 10,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'free',
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 10,
          style: {
            rules: ['background-free-label'],
            icon: {}
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 10,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'free',
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 10,
          style: {
            rules: ['background-free-label'],
            icon: {}
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 10,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'no-label',
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 10,
          style: {
            rules: ['no-label'],
            icon: {}
          }
        }
      },
      // content level 20
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 20,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'vip',
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 20,
          style: {
            rules: ['background-vip-label'],
            icon: {
              src: '/static/img/vip.svg'
            }
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 20,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'free',
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 20,
          style: {
            rules: ['background-free-label'],
            icon: {}
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 20,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'no-label',
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 20,
          style: {
            rules: ['no-label'],
            icon: {}
          }
        }
      },
      // content level 30
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 30,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'vip',
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 30,
          style: {
            rules: ['background-vip-label'],
            icon: {
              src: '/static/img/vip.svg'
            }
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 30,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'vip',
          userLevel: USER_LEVELS.REGISTERED,
          contentLevel: 30,
          style: {
            rules: ['background-vip-label'],
            icon: {
              src: '/static/img/vip.svg'
            }
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 30,
          availability: 'SVOD'
        },
        expectedResult: {
          availability: 'SVOD',
          name: 'no-label',
          userLevel: USER_LEVELS.SUBSCRIBER,
          contentLevel: 30,
          style: {
            rules: ['no-label'],
            icon: {}
          }
        }
      },
      {
        value: {
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 10,
          availability: 'TVOD'
        },
        expectedResult: {
          availability: 'TVOD',
          name: 'rent',
          userLevel: USER_LEVELS.VISITOR,
          contentLevel: 10,
          style: {
            rules: ['background-rent-label'],
            icon: {}
          }
        }
      },
      {
        value: {
          name: 'spotlight'
        },
        expectedResult: {
          name: 'spotlight',
          style: {
            rules: ['background-spotlight-label'],
            icon: {}
          }
        }
      }
    ];

    testCases.map(testCase => {
      const result = calculateLabel({ ...testCase.value });
      expect(result).toEqual(testCase.expectedResult);
    });
  });
});
