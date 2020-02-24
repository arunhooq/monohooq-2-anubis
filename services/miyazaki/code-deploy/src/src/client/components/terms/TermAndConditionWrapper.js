import React from 'react';
import { Router } from '../../routes';
import { connect } from 'react-redux';
import './tncWrapper.scss';

const mapStateToProps = state => ({
  translation: state.translation
});

class TermConditionWrapper extends React.Component {
  gotoTerms = () => {
    Router.pushRoute('terms');
  };

  gotoPolicy = company => {
    Router.pushRoute('policy', { company });
  };

  render() {
    const { translation } = this.props;
    return (
      <div className="container is-fullwidth tnc-container">
        <div className="columns is-multiline is-mobile">
          <div
            className="column is-12-mobile has-text-left tnc-container__title"
            dangerouslySetInnerHTML={{
              __html: translation.consentDefault_terms_condition
            }}
          />
          <div className="column is-12-mobile ">{this.props.children}</div>
        </div>
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(TermConditionWrapper);
