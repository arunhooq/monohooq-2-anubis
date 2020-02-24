import React from 'react';
import { connect } from 'react-redux';
import Header from '../components/shared/Header';
import ImageHeader from '../components/plans/headerImage';
import PlanSelector from '../components/plans/planSelector';
import PlanDescription from '../components/plans/planDescription';
import PlanAction from '../components/plans/planAction';
import { setPlans, selectPlan } from '../actions/planAction';
import { setInitialProps } from './../util/pages';
import { getPlanConfigs } from './../services/subscription';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';

const mapStateToProps = (state, ownProps) => {
  return {
    plans: state.plan.plans,
    selectedPlan: state.plan.selectedPlan,
    region: state.discover.region,
    tracking: state.tracking,
    userSession: state.user.userSession
  };
};

const mapDispatchToProps = dispatch => ({
  setPlans: plans => dispatch(setPlans(plans)),
  selectPlan: plan => dispatch(selectPlan(plan))
});

export class Plans extends React.Component {
  constructor(props) {
    super(props);

    this.onSelectedPLan = this.onSelectedPLan.bind(this);
    this.state = {
      planCTALabel: '',
      planCancelLabel: ''
    };
  }

  static async getInitialProps(ctx) {
    const { query } = ctx;
    const { titleId } = query;
    await setInitialProps(ctx);

    return {
      titleId
    };
  }

  getPlanConfigs = async () => {
    const configs = await getPlanConfigs({
      titleId: this.props.titleId
    });

    return configs;
  };

  async componentDidMount() {
    document.title = 'HOOQ';
    const config = await this.getPlanConfigs();
    this.props.setPlans(config.plans);
    this.setState({
      planCTALabel: config.plan_cta_label,
      planCancelLabel: config.plan_cancel_label
    });
  }

  componentDidUpdate(prevProps) {
    if (prevProps.selectedPlan.name !== this.props.selectedPlan.name) {
      this.props.selectPlan(this.props.selectedPlan);
    }

    if (this.props.plans.length > 0 && this.props.selectedPlan.name == '') {
      this.props.selectPlan(this.props.plans[0]);
    }
  }

  onSelectedPLan = e => {
    const { titleId } = this.props;
    this.props.tracking.trackGtm(`Payments_SelectPlan_Button`, {
      titleId,
      currency: e.currency,
      duration: e.duration,
      name: e.name,
      price: e.price
    });
    this.props.selectPlan(e);
  };

  render() {
    const { plans, selectedPlan, titleId, userSession } = this.props;
    const { planCTALabel, planCancelLabel } = this.state;

    const payload = {
      titleId,
      plans: plans.map(plan => {
        return {
          currency: plan.currency,
          duration: plan.duration,
          name: plan.name,
          price: plan.price
        };
      })
    };

    return (
      <React.Fragment>
        <PageLoadTracker
          trackingTag="paywall_plan_selector"
          payload={payload}
          userSession={userSession}
        />

        <Header showBack />
        <div className="plan-container">
          <ImageHeader />
          <PlanSelector onSelectedPLan={this.onSelectedPLan} plans={plans} />
          <PlanDescription plan={selectedPlan} />
          <PlanAction
            titleId={titleId}
            selectedPlan={selectedPlan}
            planCTALabel={planCTALabel}
            planCancelLabel={planCancelLabel}
          />
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Plans);
