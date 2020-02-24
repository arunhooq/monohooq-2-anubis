import React from 'react';
import { Router } from '../../routes';
import { connect } from 'react-redux';
import { setConsent } from '../../actions/detailAction';
import { setUserSubscription } from '../../actions/userAction';
import { Subscription } from './../../../common/General';
import { getDetails as getSubscriptionDetails } from './../../services/subscription';

const { NEW_USER } = Subscription;

const mapDispatchToProps = dispatch => ({
  setConsent: (movieId, showConsent, sku) =>
    dispatch(setConsent(movieId, showConsent, sku)),
  setUserSubscription: subscription =>
    dispatch(setUserSubscription(subscription))
});

const mapStateToProps = state => ({
  consent: state.detail.consent,
  translation: state.translation,
  userSession: state.user.userSession
});

const RoundedCheckbox = props => {
  const { id, name, onChange, checked, className } = props;
  return (
    <div className={className}>
      <input
        onChange={onChange}
        type="checkbox"
        id={id}
        name={name}
        checked={checked}
      />
      <label htmlFor={id} />
    </div>
  );
};

export class ConsentContent extends React.Component {
  async componentDidMount() {
    await this.updateUserSubscription();
  }

  async updateUserSubscription() {
    const subscriptionDetails = !this.props.userSession.isVisitor
      ? await getSubscriptionDetails()
      : [];
    this.props.setUserSubscription(subscriptionDetails);
  }

  gotoTerms = () => {
    this.props.setConsent(this.props.movieId, true, this.props.sku);

    Router.pushRoute('terms');
  };

  gotoPolicy = company => {
    this.props.setConsent(this.props.movieId, true, this.props.sku);

    Router.pushRoute('policy');
  };

  renderPolicyCheckbox = isRender => {
    if (isRender === false) {
      return null;
    }

    const { translation, policyChecked, onChange } = this.props;
    return (
      <div className="consent-content-item" style={{ paddingTop: '0rem' }}>
        <div style={{ display: 'flex', paddingLeft: '8px' }}>
          <RoundedCheckbox
            className="round"
            onChange={onChange}
            id="checkboxpolicy"
            name="checkboxPolicy"
            checked={policyChecked}
          />
          <span
            style={{ marginLeft: '14px', fontSize: '12px', marginTop: '0px' }}
          >
            {translation.consentPolicy}{' '}
            <span onClick={() => this.gotoPolicy('grab')}>
              <strong style={{ textDecoration: 'underline' }}>Grab</strong>
            </span>{' '}
            and{' '}
            <span onClick={() => this.gotoPolicy('hooq')}>
              <strong style={{ textDecoration: 'underline' }}>HOOQ</strong>
            </span>{' '}
            Privacy Policy
          </span>
        </div>
      </div>
    );
  };

  renderTermsCheckbox = isRender => {
    if (isRender === false) {
      return null;
    }

    const { translation, termsChecked, onChange } = this.props;

    return (
      <div className="consent-content-item" style={{ paddingTop: '0rem' }}>
        <div style={{ display: 'flex', paddingLeft: '8px' }}>
          <RoundedCheckbox
            className="round"
            onChange={onChange}
            id="checkboxterms"
            name="checkboxTerms"
            checked={termsChecked}
          />
          <span
            style={{ marginLeft: '14px', fontSize: '12px', marginTop: '0px' }}
          >
            {translation.consentTerm_agree}{' '}
            <span onClick={() => this.gotoTerms()}>
              <strong style={{ textDecoration: 'underline' }}>
                {translation.consentTerm_condition}
              </strong>
            </span>
          </span>
        </div>
      </div>
    );
  };

  shouldRenderCheckboxes = () => {
    const { userSession } = this.props;

    switch (userSession.status) {
      case NEW_USER:
        return false;
      default:
        return true;
    }
  };

  render() {
    let { translation, consent, userSession } = this.props;
    const isRender = this.shouldRenderCheckboxes();

    const { device } = userSession;
    if (
      consent.contentOverride &&
      consent.contentOverride.availableFor.includes(device.os.toLowerCase())
    ) {
      if (consent.contentOverride.consentContent) {
        translation.consentContent = consent.contentOverride.consentContent;
      }
    }

    return (
      <React.Fragment>
        <div className="consent-content-item">
          <div className="consent-content-h1 is-size-5 has-text-centered has-text-weight-bold">
            {translation.consentHeader}
          </div>
        </div>

        <div className="consent-content-item" style={{ paddingTop: '0px' }}>
          <div style={{ paddingLeft: '1.2rem', paddingRight: '0.1rem' }}>
            <ul className="consent-promotion">
              {translation.consentContent.split(',\n').map((el, index) => {
                return <li key={index}>{el.replace("'", '')}</li>;
              })}
            </ul>
          </div>
        </div>

        {this.renderPolicyCheckbox(isRender)}
        {this.renderTermsCheckbox(isRender)}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ConsentContent);
