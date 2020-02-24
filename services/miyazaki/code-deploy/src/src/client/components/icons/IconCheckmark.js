import React from 'react';

const IconCheckmark = props => (
  <svg viewBox="0 0 224 224" width={48} height={48} {...props}>
    <g
      fill="none"
      strokeMiterlimit={10}
      fontFamily="none"
      fontWeight="none"
      fontSize="none"
      textAnchor="none"
      style={{
        mixBlendMode: 'normal',
        stroke: props.stroke
      }}
    >
      <path d="M0 224V0h224v224z" />
      <path
        d="M189.401 49.401L84 154.802l-40.068-40.068-13.198 13.198L84 181.198 202.599 62.599z"
        fill="#951b81"
      />
    </g>
  </svg>
);

export default IconCheckmark;
