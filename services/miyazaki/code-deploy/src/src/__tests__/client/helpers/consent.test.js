import Consent from './../../../client/helpers/consent';

describe('getScreenWidth', () => {
  it('should return the width of window.screen', () => {
    // stub window.screen
    const screen = {
      width: 320,
      height: 640,
      orientation: 'portrait-primary'
    };

    const deviceWidth = Consent.getScreenWidth(screen);
    expect(deviceWidth).toEqual(640);
  });
});

describe('getScreenHeight', () => {
  it('should return the height of window.screen', () => {
    // stub window.screen
    const screen = {
      width: 320,
      height: 640,
      orientation: 'portrait-primary'
    };

    const deviceHeight = Consent.getScreenHeight(screen);
    expect(deviceHeight).toEqual(320);
  });
});
