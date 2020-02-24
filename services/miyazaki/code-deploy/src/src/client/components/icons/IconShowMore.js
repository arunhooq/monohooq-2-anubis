import React from 'react';

const IconShowMore = props => (
  <svg width={24} height={12} {...props}>
    <path
      fill="none"
      style={{
        stroke: props.stroke,
        strokeWidth: props.strokeWidth
      }}
      d="M4 8.5a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5zm8 0a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5zm8 0a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5z"
    />
  </svg>
);

export default IconShowMore;
