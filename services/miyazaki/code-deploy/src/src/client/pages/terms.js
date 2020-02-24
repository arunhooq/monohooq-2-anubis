import NoSSR from 'react-no-ssr';
import React from 'react';
import Header from '../components/shared/Header';
import {
  INDONESIA,
  SINGAPORE,
  PHILIPPINE,
  THAILAND,
  INDIA
} from '../constants/region';
import TermIndonesia from '../components/terms/idId';
import TermSingapore from '../components/terms/enSg';
import TermPhilippine from '../components/terms/enPh';
import TermThailand from '../components/terms/thTh';
import TermIndia from '../components/terms/enIn';
import { setInitialProps } from '../util/pages';

import '../components/terms/term.scss';

class Terms extends React.Component {
  static async getInitialProps(ctx) {
    await setInitialProps(ctx);

    const { store } = ctx;
    const region = store.getState().discover.region;
    return { region };
  }

  componentDidMount() {
    document.title = 'Term & Policy';
  }

  render() {
    const { region } = this.props;
    return (
      <React.Fragment>
        <Header showBack />
        <NoSSR>
          <div className="container">
            {region === INDONESIA && <TermIndonesia />}
            {region === SINGAPORE && <TermSingapore />}
            {region === PHILIPPINE && <TermPhilippine />}
            {region === THAILAND && <TermThailand />}
            {region === INDIA && <TermIndia />}
          </div>
        </NoSSR>
      </React.Fragment>
    );
  }
}

export default Terms;
