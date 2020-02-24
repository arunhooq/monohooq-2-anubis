/* eslint-env jest */

import { shallow } from 'enzyme';
import React from 'react';
import { ErrorModal } from '../../../../client/components/shared/ErrorModal';

describe('<ErrorModal />', () => {
  test('should render the modal', () => {
    const props = {
      open: false,
      onCloseModal: () => {},
      errorModal: { code: 'USR-2106' },
      uiConfig: {
        errorModal: {
          cta: [
            {
              fontColor: '#951b81',
              label: 'modal_okay'
            }
          ]
        },
        background: 'white',
        'body-background-color': 'white',
        'body-color': 'grey-lighter',
        'family-sans-serif': ['Roboto', 'Helvetica', 'Arial', 'sans-serif'],
        'grey-lighter': '#dedede',
        link: 'primary',
        'link-focus': 'primary',
        'link-hover': 'primary',
        'link-visited': 'primary',
        primary: 'purple',
        purple: '#673ab7',
        'tabs-border-bottom-color': 'purple',
        'tabs-border-bottom-width': '5px',
        text: 'grey-darker'
      },
      translation: {
        'USR-2106': 'XXX'
      }
    };
    const wrapper = shallow(<ErrorModal {...props} />);
    expect(wrapper.find('.alert-content').exists()).toEqual(true);
    expect(wrapper.contains(<div className="alert-content">XXX</div>)).toEqual(
      true
    );
  });

  test('if `errorModal` missing from props throws an error', () => {
    const props = {
      open: true,
      onCloseModal: () => {},
      uiConfig: {
        errorModal: {
          cta: [
            {
              fontColor: '#951b81',
              label: 'modal_okay'
            }
          ]
        },
        background: 'white',
        'body-background-color': 'white',
        'body-color': 'grey-lighter',
        'family-sans-serif': ['Roboto', 'Helvetica', 'Arial', 'sans-serif'],
        'grey-lighter': '#dedede',
        link: 'primary',
        'link-focus': 'primary',
        'link-hover': 'primary',
        'link-visited': 'primary',
        primary: 'purple',
        purple: '#673ab7',
        'tabs-border-bottom-color': 'purple',
        'tabs-border-bottom-width': '5px',
        text: 'grey-darker'
      }
    };

    expect(() => {
      shallow(<ErrorModal {...props} />);
    }).toThrow();
  });
});
