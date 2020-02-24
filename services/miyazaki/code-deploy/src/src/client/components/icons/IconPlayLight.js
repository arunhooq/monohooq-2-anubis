import React from 'react';

const IconPlayLight = props => (
  <svg width="1.5rem" height="1.5rem" viewBox="0 0 24 24" {...props}>
    <g
      fill="none"
      fillRule="evenodd"
      transform="translate(1 1)"
      style={{
        stroke: props.stroke
      }}
    >
      <circle cx={11} cy={11} r={10.5} />
      <path d="M9.336 5.712l6.92 4.086a1.1 1.1 0 0 1 .455.886c0 .35-.17.677-.497.914L9.359 15.64a1.185 1.185 0 0 1-1.197.122 1.112 1.112 0 0 1-.662-1.008v-8.14c0-.434.261-.825.662-1.007a1.194 1.194 0 0 1 1.174.105z" />
    </g>
  </svg>
);

export default IconPlayLight;
