import React from 'react';

export default class Health extends React.Component {
  constructor(props) {
    super(props);
  }

  async componentDidMount() {
    document.title = 'HOOQ Health Check';
  }

  render() {
    return (
      <React.Fragment>
        <h1>Client-side health check</h1>
      </React.Fragment>
    );
  }
}
