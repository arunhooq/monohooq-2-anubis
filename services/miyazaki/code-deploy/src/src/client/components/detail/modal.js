import React from 'react';
import PropTypes from 'prop-types';
import Modal from 'react-bulma-components/lib/components/modal';
import Button from 'react-bulma-components/lib/components/button';

class OpenModal extends React.Component {
  static propTypes = {
    modal: PropTypes.object
  };

  static defaultProps = {
    modal: {}
  };

  state = {
    show: false
  };

  open = () => this.setState({ show: true });
  close = () => this.setState({ show: false });

  render() {
    return (
      <div>
        <Button onClick={this.open}>Open</Button>
        <Modal
          show={this.state.show}
          onClose={this.close}
          {...this.props.modal}
        >
          {this.props.children}
        </Modal>
      </div>
    );
  }
}

export default OpenModal;
