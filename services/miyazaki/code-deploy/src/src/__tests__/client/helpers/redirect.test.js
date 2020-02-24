import { redirectErrorPage } from './../../../client/helpers/redirect';
import sinon from 'sinon';

jest.mock('./../../../client/routes');
const Routes = require('./../../../client/routes');
const Router = Routes.Router;
const logger = require('./../../../common/ClientLogger');

describe('redirect', () => {
  describe('redirectErrorPage()', () => {
    beforeEach(() => {
      sinon.spy(logger.default, 'error');
    });

    afterEach(() => {
      sinon.restore();
    });

    test('should thrown an Error if the `error` param is not an instance of Error', () => {
      expect(() => {
        redirectErrorPage(true, {}, {}, {});
      }).toThrow(/The {error} parameter should be an instance of an Error/);
    });

    test('test the server redirect', () => {
      const res = {
        writeHead: jest.fn(),
        end: jest.fn()
      };

      redirectErrorPage(true, res, {}, new Error('Test'));

      expect(logger.default.error.calledOnce).toBe(true);
      expect(res.writeHead.mock.calls.length).toBe(1);
      expect(res.end.mock.calls.length).toBe(1);
    });

    test('test the client redirect', () => {
      const res = {
        writeHead: jest.fn(),
        end: jest.fn()
      };

      redirectErrorPage(false, res, {}, new Error('Test'));

      expect(logger.default.error.calledOnce).toBe(true);
      expect(res.writeHead.mock.calls.length).toBe(0);
      expect(res.end.mock.calls.length).toBe(0);
      expect(Router.pushRoute.mock.calls.length).toBe(1);
    });
  });
});
