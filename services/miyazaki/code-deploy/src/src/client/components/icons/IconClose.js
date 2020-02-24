import React from 'react';

const IconClose = props => (
  <svg
    width={props.width || '1rem'}
    height={props.height || '1rem'}
    {...props}
    viewBox="0 0 16 16"
    version="1.1"
    xmlns="http://www.w3.org/2000/svg"
    xmlnsXlink="http://www.w3.org/1999/xlink"
  >
    <title>ic-close</title>
    <desc>Created with Sketch.</desc>
    <defs />
    <g id="Page-1" stroke="none" strokeWidth="1" fill="none" fillRule="evenodd">
      <g
        id="ic-close"
        fill={props.fill || '#1E1E1E'}
        stroke={props.stroke || '#1E1E1E'}
      >
        <rect
          id="Rectangle"
          transform="translate(8.000000, 8.000000) rotate(45.000000) translate(-8.000000, -8.000000) "
          x="-1.5"
          y="7.5"
          width="19"
          height="1"
          rx="0.5"
        />
        <rect
          id="Rectangle"
          transform="translate(8.000000, 8.000000) rotate(-45.000000) translate(-8.000000, -8.000000) "
          x="-1.5"
          y="7.5"
          width="19"
          height="1"
          rx="0.5"
        />
      </g>
    </g>
  </svg>
);

export default IconClose;
