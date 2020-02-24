import { Component } from 'react';
import tracking from './tracking';

class PageLoadTracker extends Component {
  constructor(props) {
    super(props);

    this.tracking = tracking;
    this.tracking.setUserSession(this.props.userSession);
  }

  trackPageLoad(props) {
    this.tracking.trackGtm(`page_load_${props.trackingTag}`, props.payload);
  }

  componentDidMount() {
    this.trackPageLoad(this.props);
  }

  render() {
    return null;
  }
}

export default PageLoadTracker;
