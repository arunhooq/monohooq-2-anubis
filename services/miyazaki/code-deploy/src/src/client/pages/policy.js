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
import HooqPolicyIndonesia from '../components/policy/hooq/idId';
import HooqPolicySingapore from '../components/policy/hooq/enSg';
import HooqPolicyPhilippine from '../components/policy/hooq/enPh';
import HooqPolicyThailand from '../components/policy/hooq/thTh';
import HooqPolicyIndia from '../components/policy/hooq/enIn';

import GrabPolicyIndonesia from '../components/policy/grab/idId';
import GrabPolicySingapore from '../components/policy/grab/enSg';
import GrabPolicyPhilippine from '../components/policy/grab/enPh';
import GrabPolicyThailand from '../components/policy/grab/thTh';
import GrabPolicyIndia from '../components/policy/grab/enIn';
import { setInitialProps } from '../util/pages';

import '../components/policy/policy.scss';

class Policy extends React.Component {
  static async getInitialProps(ctx) {
    const { store, query } = ctx;
    await setInitialProps(ctx);

    const region = store.getState().discover.region;
    let company = query.company || null;
    return { region, company };
  }

  render() {
    const { region, company } = this.props;
    return (
      <React.Fragment>
        <Header showBack />
        <NoSSR>
          <div className="container">
            {region === INDONESIA && company === 'hooq' && (
              <HooqPolicyIndonesia />
            )}
            {region === SINGAPORE && company === 'hooq' && (
              <HooqPolicySingapore />
            )}
            {region === PHILIPPINE && company === 'hooq' && (
              <HooqPolicyPhilippine />
            )}
            {region === THAILAND && company === 'hooq' && (
              <HooqPolicyThailand />
            )}
            {region === INDIA && company === 'hooq' && <HooqPolicyIndia />}

            {region === INDONESIA && company === 'grab' && (
              <GrabPolicyIndonesia />
            )}
            {region === SINGAPORE && company === 'grab' && (
              <GrabPolicySingapore />
            )}
            {region === PHILIPPINE && company === 'grab' && (
              <GrabPolicyPhilippine />
            )}
            {region === THAILAND && company === 'grab' && (
              <GrabPolicyThailand />
            )}
            {region === INDIA && company === 'grab' && <GrabPolicyIndia />}
          </div>
        </NoSSR>
      </React.Fragment>
    );
  }
}

export default Policy;
