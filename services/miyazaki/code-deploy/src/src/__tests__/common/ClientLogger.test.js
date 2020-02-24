/* eslint-env jest */

import logger from './../../common/ClientLogger';

const mockedNr = {
  error: jest.fn(),
  trace: jest.fn()
};

test('the initial value of `transports` should be an empty array', () => {
  expect(logger.getTransports()).toHaveLength(0);
});

test('fusing a transport into the logger will make the `transports` length to non empty', () => {
  logger.fuse(mockedNr);
  expect(logger.getTransports()).toHaveLength(1);
});

test('logging an error once will called the mocked error(...) one time', () => {
  logger.error(new Error('xxx'));
  expect(mockedNr.error).toHaveBeenCalledTimes(1);
});

test('logging an error twice will called the mocked error(...) one time', () => {
  logger.error(new Error('xxx'));
  logger.error(new Error('xxx'));
  expect(mockedNr.error).toHaveBeenCalledTimes(2);
});
