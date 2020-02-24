import React from 'react';
import { connect } from 'react-redux';
import { Container } from 'react-bulma-components';
import { Router } from '../../routes';
import './categories.scss';

const mapStateToProps = state => ({
  discover: state.discover
});

class Category extends React.Component {
  handleCategoryClick(collection) {
    Router.pushRoute('collection', {
      collectionId: collection.collectionId
    });
  }

  render() {
    const quicklinks = this.props.row.hasOwnProperty('data')
      ? this.props.row.data
      : [];
    const primaryColor = '#951b81';

    return (
      <Container className="category-wrapper">
        {quicklinks.map((link, index) => {
          return (
            <div
              onClick={() => this.handleCategoryClick(link)}
              key={index}
              style={{ marginRight: '0.5em' }}
            >
              <span
                className="button is-rounded is-outlined category-item"
                style={{
                  // TODO retrieve the value from the config
                  color: primaryColor,
                  borderColor: primaryColor
                }}
              >
                {link.name}
              </span>
            </div>
          );
        })}
      </Container>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(Category);
