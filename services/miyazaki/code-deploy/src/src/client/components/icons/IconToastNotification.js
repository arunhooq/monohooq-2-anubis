import React from 'react';

const IconToastNotification = props => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width="56"
    height="56"
    viewBox="0 0 56 56"
  >
    <defs>
      <filter
        id="a"
        width="120.6%"
        height="200%"
        x="-11.6%"
        y="-54.5%"
        filterUnits="objectBoundingBox"
      >
        <feOffset in="SourceAlpha" result="shadowOffsetOuter1" />
        <feGaussianBlur
          in="shadowOffsetOuter1"
          result="shadowBlurOuter1"
          stdDeviation="8"
        />
        <feColorMatrix
          in="shadowBlurOuter1"
          result="shadowMatrixOuter1"
          values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.5 0"
        />
        <feMerge>
          <feMergeNode in="shadowMatrixOuter1" />
          <feMergeNode in="SourceGraphic" />
        </feMerge>
      </filter>
      <filter
        id="b"
        width="239.3%"
        height="233.9%"
        x="-59.8%"
        y="-74.1%"
        filterUnits="objectBoundingBox"
      >
        <feOffset dy="-4" in="SourceAlpha" result="shadowOffsetOuter1" />
        <feGaussianBlur
          in="shadowOffsetOuter1"
          result="shadowBlurOuter1"
          stdDeviation="6.5"
        />
        <feColorMatrix
          in="shadowBlurOuter1"
          result="shadowMatrixOuter1"
          values="0 0 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0.8 0"
        />
        <feMerge>
          <feMergeNode in="shadowMatrixOuter1" />
          <feMergeNode in="SourceGraphic" />
        </feMerge>
      </filter>
      <filter
        id="c"
        width="252.4%"
        height="253.6%"
        x="-75.3%"
        y="-75.7%"
        filterUnits="objectBoundingBox"
      >
        <feOffset dx="-1" dy="3" in="SourceAlpha" result="shadowOffsetOuter1" />
        <feGaussianBlur
          in="shadowOffsetOuter1"
          result="shadowBlurOuter1"
          stdDeviation="3.5"
        />
        <feColorMatrix
          in="shadowBlurOuter1"
          result="shadowMatrixOuter1"
          values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.3 0"
        />
        <feMerge>
          <feMergeNode in="shadowMatrixOuter1" />
          <feMergeNode in="SourceGraphic" />
        </feMerge>
      </filter>
    </defs>
    <g fill="none" fillRule="evenodd" filter="url(#b)">
      <path
        fill="#6A1974"
        d="M23.126 29.759l16.593-7.224v19.609L23.126 50.4z"
      />
      <path fill="#FBBB21" d="M31.422 26.663l3.111-2.064v19.609l-3.11 2.064z" />
      <path fill="#4F1556" d="M6.533 22.535l16.593 7.224V50.4L6.533 42.144z" />
      <path
        fill="#BA8505"
        d="M13.793 26.663l-3.112-2.064v19.609l3.112 2.064z"
      />
      <path
        fill="#2E1C2E"
        d="M23.126 17.374l16.593 5.16-16.593 8.257-16.593-8.256z"
      />
      <g filter="url(#c)" transform="translate(24.785 7.467)">
        <path
          fill="#5A1460"
          d="M21.775 7.556l4.627 14.752-19.057-7.021L3.037.807z"
        />
        <path fill="#491050" d="M3.037.806l5.143 14.48-3 5.463L.246 6.782z" />
        <path
          fill="#730381"
          d="M8.359 15.287l17.238 6.005-3.042 4.918-17.238-5.843z"
        />
        <path
          fill="#BA8505"
          d="M6.173 9.705l9.526 1.54-1-1.293-9.382-2.834-3.872 3.279 1.664 3.046z"
        />
        <path
          fill="#FBBB21"
          d="M19.534 13.865c-1.923-.991-3.44-2.6-3.449-3.247-.013-.897 3.218-1.328 4.019-1.858 1.377-.912 3.39-.52 4.493.879 1.104 1.398.882 3.27-.495 4.184-1.378.912-3.464 1.44-4.568.042zm-.709-1.306c.6.759 1.852.393 2.701-.17.849-.562 1.051-1.633.452-2.392-.6-.759-1.774-.918-2.623-.356-.493.326-2.419.674-2.445 1.183-.019.368.816 1.233 1.915 1.735z"
        />
        <path
          fill="#FBBB21"
          d="M11.242 9.485c2.181 1.157 4.702 1.47 5.334 1.01.876-.636-.761-4.1-.757-5.266.007-2.005-1.653-3.683-3.707-3.747-2.055-.063-3.727 1.51-3.734 3.516-.007 2.006.81 4.423 2.864 4.487zm1.714-.255c-1.116-.035-1.558-1.522-1.554-2.758.005-1.235.913-2.209 2.029-2.174 1.115.034 2.016 1.064 2.012 2.3-.003.717.885 2.848.41 3.243-.344.284-1.713.097-2.897-.611z"
        />
        <path
          fill="#FBBB21"
          d="M17.31 18.532l-2.867-8.276 2.359.712 3.34 8.308-3.34 4.864-3.113-.816z"
        />
      </g>
    </g>
  </svg>
);

export default IconToastNotification;
