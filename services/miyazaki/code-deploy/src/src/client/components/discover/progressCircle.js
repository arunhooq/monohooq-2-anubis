import React from 'react';
import './progressCircle.scss';

class ProgressCircle extends React.Component {
  render() {
    const { progress } = this.props;

    return (
      <svg
        className="circle-chart"
        viewBox="0 0 33.83098862 33.83098862"
        width="100%"
        height="100%"
        xmlns="http://www.w3.org/2000/svg"
      >
        <defs>
          <pattern id="play" width="40" height="40">
            <path
              fill="#f2f2f2"
              d="M22.745 16.251a.516.516 0 0 1-.192.185l-.015.014-10.175 5.874a.505.505 0 0 1-.687-.184.501.501 0 0 1-.062-.253l-.005-.023V10.136l.005-.023c0-.1.02-.18.062-.253a.505.505 0 0 1 .687-.184l10.175 5.874.015.014c.09.053.15.114.192.185a.507.507 0 0 1 .068.251.507.507 0 0 1-.068.251m1.393-1.307l-.001-.001a2.098 2.098 0 0 0-.732-.737l-.04-.036-10.197-5.888a2.112 2.112 0 0 0-2.886.775c-.184.32-.262.665-.27.994l-.012.062v11.774l.012.062c.008.329.086.675.27.994a2.112 2.112 0 0 0 2.886.775l10.198-5.888.04-.036c.279-.17.543-.412.731-.737v-.001c.191-.33.285-.698.285-1.056 0-.358-.094-.725-.284-1.056"
            />
          </pattern>
        </defs>
        <circle
          className="circle-chart__background"
          stroke="#f2f2f2"
          strokeWidth="2"
          fill="url(#play)"
          cx="16.91549431"
          cy="16.91549431"
          r="15.91549431"
        />
        <circle
          className="circle-chart__circle"
          stroke={`${progress === 0 ? '#f2f2f2' : '#951b81'}`}
          strokeWidth="2"
          strokeDasharray={`${progress},20000`}
          strokeLinecap="round"
          fill="none"
          cx="16.91549431"
          cy="16.91549431"
          r="15.91549431"
        />
      </svg>
    );
  }
}

export default ProgressCircle;
