import React from 'react';
import IconPremiumInfinity from './IconPremiumInfinity';
import './IconVip.scss';

const IconVip = props => (
  <div className="vim-wrapper" {...props}>
    <IconPremiumInfinity />
    <span className="vip-label">VIP</span>
  </div>
);

export default IconVip;
