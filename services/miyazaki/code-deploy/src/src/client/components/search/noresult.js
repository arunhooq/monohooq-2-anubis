import React from 'react';
import './noresult.scss';

class NoResult extends React.Component {
  render() {
    const { keyword } = this.props;
    return (
      <div className="section no-result__section">
        <div className="container no-result__container">
          <p>Oops, no results match</p>
          <p>“{keyword}”</p>
          <div className="no-result__message">
            Please check your spelling or try searching for something else.
          </div>
        </div>
      </div>
    );
  }
}

export default NoResult;
