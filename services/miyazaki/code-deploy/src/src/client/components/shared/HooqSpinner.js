import React from 'react';
import './HooqSpinner.scss';

const HooqSpinner = props =>
  props.show ? (
    <div className="loading-overlay">
      <div className="loading-spinner" />
    </div>
  ) : null;

export default HooqSpinner;
