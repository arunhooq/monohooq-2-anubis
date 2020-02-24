// FIXME
const {
  getPlanPropertiesTranslation,
  getUserPhoneNumber,
  parsePhoneNumberFromTheFormattedEmail
} = require('../../../server/Helpers/Config');
const { testTable } = require('../../../__mocks__/server/helpers/Config');

const translation = require('../../../__mocks__/translation');

describe.skip('getPlanPropertiesTranslation', () => {
  test('should return the expected response based on the mocked data', () => {
    testTable.forEach(tt => {
      const received = getPlanPropertiesTranslation(
        tt.sku,
        translation.default
      );
      expect(received).toEqual(tt.planProperties);
    });
  });
});

describe.skip('getUserPhoneNumber()', () => {
  test('user details with an email field attached', () => {
    const userDetail = {
      accountNumber: '20191031044055140',
      registerNumber: '20191031044055140',
      cpCustomerId: '88692d77-f76a-40a9-90ea-d124cdfa35e7',
      billingMode: 'Monthly',
      billDeliveryType: 'Paper',
      accountStatus: 'Active',
      accountType: 'Residential',
      dmaId: '001',
      dmaName: 'Los Angles',
      salesMethod: 'Call Center',
      directAccount: 'F',
      country: 'ID',
      status: 'SUCCESS',
      deviceId: '1930decd-1bd1-44ff-963e-cf35d633156c',
      userLevel: 20,
      email: 'e064f357-f4bc-4a22-bc7a-a4316233d1b7+81328305756@grab.com'
    };

    const ctx = {
      state: {
        partnerConfiguration: {
          CustomConfig: {
            actions: {
              user: {
                primaryId: {
                  type: 'phoneNumber',
                  extractFromField: 'email',
                  rules: {
                    regex: {
                      pattern: '\\+([0-9]+)?'
                    }
                  }
                }
              }
            }
          }
        }
      }
    };

    const expectedResult = {
      accountNumber: '20191031044055140',
      registerNumber: '20191031044055140',
      cpCustomerId: '88692d77-f76a-40a9-90ea-d124cdfa35e7',
      billingMode: 'Monthly',
      billDeliveryType: 'Paper',
      accountStatus: 'Active',
      accountType: 'Residential',
      dmaId: '001',
      dmaName: 'Los Angles',
      salesMethod: 'Call Center',
      directAccount: 'F',
      country: 'ID',
      status: 'SUCCESS',
      deviceId: '1930decd-1bd1-44ff-963e-cf35d633156c',
      userLevel: 20,
      email: 'e064f357-f4bc-4a22-bc7a-a4316233d1b7+81328305756@grab.com',
      phoneNumber: '81328305756'
    };

    const result = getUserPhoneNumber(ctx, userDetail);
    expect(result).toStrictEqual(expectedResult);
    expect(result.email).toBeDefined();
  });

  test('user details without an email field attached should set the phoneNumber to empty string', () => {
    const userDetail = {
      accountNumber: '20191031044055140',
      registerNumber: '20191031044055140',
      cpCustomerId: '88692d77-f76a-40a9-90ea-d124cdfa35e7',
      billingMode: 'Monthly',
      billDeliveryType: 'Paper',
      accountStatus: 'Active',
      accountType: 'Residential',
      dmaId: '001',
      dmaName: 'Los Angles',
      salesMethod: 'Call Center',
      directAccount: 'F',
      country: 'ID',
      status: 'SUCCESS',
      deviceId: '1930decd-1bd1-44ff-963e-cf35d633156c',
      userLevel: 20
    };

    const ctx = {
      state: {
        partnerConfiguration: {
          CustomConfig: {
            actions: {
              user: {
                primaryId: {
                  type: 'phoneNumber',
                  extractFromField: 'email',
                  rules: {
                    regex: {
                      pattern: '\\+([0-9]+)?'
                    }
                  }
                }
              }
            }
          }
        }
      }
    };

    const expectedResult = {
      accountNumber: '20191031044055140',
      registerNumber: '20191031044055140',
      cpCustomerId: '88692d77-f76a-40a9-90ea-d124cdfa35e7',
      billingMode: 'Monthly',
      billDeliveryType: 'Paper',
      accountStatus: 'Active',
      accountType: 'Residential',
      dmaId: '001',
      dmaName: 'Los Angles',
      salesMethod: 'Call Center',
      directAccount: 'F',
      country: 'ID',
      status: 'SUCCESS',
      deviceId: '1930decd-1bd1-44ff-963e-cf35d633156c',
      userLevel: 20,
      phoneNumber: ''
    };

    const result = getUserPhoneNumber(ctx, userDetail);
    expect(result).toStrictEqual(expectedResult);
    expect(result.email).toBeUndefined();
  });
});

describe.skip('parsePhoneNumberFromTheFormattedEmail()', () => {
  test('parsing valid formatted email should return the parsed the mobile phone number', () => {
    const primaryId = {
      type: 'phoneNumber',
      extractFromField: 'email',
      rules: {
        regex: {
          pattern: '\\+([0-9]+)?'
        }
      }
    };

    const sampleFormattedEmail =
      'e064f357-f4bc-4a22-bc7a-a4316233d1b7+81328305756@xxx.com';
    const result = parsePhoneNumberFromTheFormattedEmail(
      sampleFormattedEmail,
      primaryId.rules.regex.pattern
    );
    expect(result).toBe('81328305756');
  });

  test('parsing invalid formatted email should not successfully parsed the mobile phone number', () => {
    const primaryId = {
      type: 'phoneNumber',
      extractFromField: 'email',
      rules: {
        regex: {
          pattern: '\\+([0-9]+)?'
        }
      }
    };

    const sampleFormattedEmail =
      'e064f357-f4bc-4a22-bc7a-a4316233d1b7+8132830asda5756@xxx.com';
    const result = parsePhoneNumberFromTheFormattedEmail(
      sampleFormattedEmail,
      primaryId.rules.regex.pattern
    );
    expect(result !== '81328305756').toBeTruthy();
  });

  test('passing formattedEmail with an empty string will return incorrect result', () => {
    const primaryId = {
      type: 'phoneNumber',
      extractFromField: 'email',
      rules: {
        regex: {
          pattern: '\\+([0-9]+)?'
        }
      }
    };

    const sampleFormattedEmail = '';
    const result = parsePhoneNumberFromTheFormattedEmail(
      sampleFormattedEmail,
      primaryId.rules.regex.pattern
    );
    expect(result !== '81328305756').toBeTruthy();
  });

  test('if the formattedEmail is undefined just return empty string', () => {
    const primaryId = {
      type: 'phoneNumber',
      extractFromField: 'email',
      rules: {
        regex: {
          pattern: '\\+([0-9]+)?'
        }
      }
    };

    const sampleFormattedEmail = '';
    const result = parsePhoneNumberFromTheFormattedEmail(
      sampleFormattedEmail,
      primaryId.rules.regex.pattern
    );
    expect(result).toBe('');
  });
});
