import React from 'react';
import ReactDragDrawer from 'react-drag-drawer';
import { connect } from 'react-redux';
import { setCast } from '../../actions/menuAction';
import './Drawer.scss';

const mapStateToProps = state => ({
  translation: state.translation,
  menu: state.menu
});

const mapDispatchToProps = dispatch => ({
  setCast: open => dispatch(setCast(open))
});

export class Drawer extends React.Component {
  constructor(props) {
    super(props);
  }

  toggle = () => {
    const { menu } = this.props;
    this.props.setCast(!menu.cast);
  };

  render() {
    const { menu, children } = this.props;

    return (
      <ReactDragDrawer
        open={menu.cast || false}
        onRequestClose={() => this.toggle()}
        containerElementClass="drawer__container"
        modalElementClass="drawer__modal"
        // parentElement={document.getElementById('__next')}
      >
        <div className="drawer__handle" />
        <div className="drawer__content">{children}</div>
      </ReactDragDrawer>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Drawer);
