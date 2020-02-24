import React from 'react';
import { Router } from '../../routes';
import IconClose from '../../components/icons/IconClose';
import './closeButton.scss';

class CloseButton extends React.Component {
  handleOnClick = backToHome => {
    if (backToHome) {
      Router.pushRoute('discover');
    } else {
      Router.back();
    }
  };

  render() {
    const { backToHome } = this.props;
    return (
      <div
        onClick={() => this.handleOnClick(backToHome)}
        className="close-button"
      >
        <IconClose
          className="shadow"
          fill={'#FFFFFF'}
          stroke={'#FFFFFF'}
          width={'1.5rem'}
          height={'1.5rem'}
        />
      </div>
    );
  }
}

export default CloseButton;
