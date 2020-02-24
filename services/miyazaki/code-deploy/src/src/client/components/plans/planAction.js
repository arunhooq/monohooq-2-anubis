import React from 'react';
import { connect } from 'react-redux';
import { Router } from './../../routes';

const mapStateToProps = (state, ownProps) => {
  return { tracking: state.tracking };
};

class PlanAction extends React.Component {
  handleClick = () => {
    const { titleId, selectedPlan } = this.props;
    this.props.tracking.trackGtm(`button_tap_payment_planselector`, {
      titleId,
      currency: selectedPlan.currency,
      duration: selectedPlan.duration,
      name: selectedPlan.name,
      price: selectedPlan.price
    });
    window.location.href = selectedPlan.paymentUrl;
  };

  handleCancelClick = () => {
    Router.back();
  };

  render() {
    const { planCTALabel, planCancelLabel } = this.props;
    return (
      <footer className="plan-selector-footer">
        <div className="content has-text-centered">
          <button
            className="button plan-selector-btn has-background-color-primary"
            onClick={this.handleClick}
          >
            <span>{planCTALabel}</span>
          </button>
          <button
            className="button plan-selector-btn is-white"
            onClick={this.handleCancelClick}
          >
            <span>{planCancelLabel}</span>
          </button>
        </div>
      </footer>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(PlanAction);
