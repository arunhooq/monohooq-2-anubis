const { getUserDetail } = require('../../../server/Helpers/User');
const { testTable } = require('../../../__mocks__/server/helpers/User');

describe('getUserDetail', () => {
  it('Should return the expected userDetail', () => {
    Object.values(testTable).forEach(async tt => {
      const received = await getUserDetail(tt.ctx, tt.session, tt.visitorToken);

      expect(received.partnerId).toEqual(tt.expected.partnerId);
      expect(received.spAccountId).toEqual(tt.expected.spAccountId);
      expect(received.visitorId).toEqual(tt.expected.visitorId);
    });
  });
});
