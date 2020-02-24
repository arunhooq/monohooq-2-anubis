import React from 'react';
import './planSelector.scss';

export const PlanBenefits = props => {
  return (
    <ul className="plan-description-item">
      {props.benefits.map((text, index) => (
        <li key={index}>{text}</li>
      ))}
    </ul>
  );
};

class PlanDescription extends React.Component {
  render() {
    const { name, benefits, price } = this.props.plan;

    return (
      <div className="plan-description">
        <div className="level is-mobile">
          <div className="caption level-left">{`${name}`}</div>
          <div className="caption level-right">{price}</div>
        </div>
        <PlanBenefits benefits={benefits} />
      </div>
    );
  }
}

export default PlanDescription;
