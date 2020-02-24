/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import { FailedPaymentModal } from '../../../../client/components/detail/failedPaymentModal';

describe('<FailedPaymentModal />', () => {
  test('should have "failed-payment-modal__wrapper" css class', () => {
    const props = {
      open: true,
      toggleModal: () => {},
      config: { failedPaymentModal: { cta: 'cta' } },
      tracking: { trackGtm: null },
      translation: {
        payment_failed_linking: 'Test',
        payment_failed_try: 'test'
      }
    };
    const wrapper = shallow(<FailedPaymentModal {...props} />);

    expect(wrapper.find('.failed-payment-modal__wrapper').exists()).toEqual(
      true
    );
    expect(wrapper.find('.failed-payment-modal__header').exists()).toEqual(
      true
    );
    expect(wrapper.find('.failed-payment-modal__content').exists()).toEqual(
      true
    );
  });
});
