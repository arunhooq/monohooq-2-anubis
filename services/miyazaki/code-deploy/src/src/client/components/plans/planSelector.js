import React from 'react';
import './planSelector.scss';

export function Selector(props) {
  const { selected, name, price, currency } = props;
  return (
    <div className="selector-wrapper has-text-centered">
      <p className={selected ? 'selector-title active' : 'selector-title'}>
        {name}
      </p>
      <p className={selected ? 'selector-price active' : 'selector-price'}>
        {price}
      </p>
    </div>
  );
}

class PlanSelector extends React.Component {
  state = {
    selected: 0
  };

  handleSelectedPlan = (el, index) => {
    this.setState({
      selected: index
    });
    this.props.onSelectedPLan(el);
  };

  render() {
    const { plans } = this.props;
    const { selected } = this.state;

    return (
      <nav className="plan-selector level is-mobile">
        {plans.map((el, index) => (
          <div
            className={
              selected === index
                ? 'is-marginless level-item active'
                : 'is-marginless level-item'
            }
            key={index}
            onClick={() => this.handleSelectedPlan(el, index)}
          >
            <Selector
              name={el.name}
              currency={el.currency}
              price={el.price}
              selected={selected === index}
            />
          </div>
        ))}
      </nav>
    );
  }
}

export default PlanSelector;
