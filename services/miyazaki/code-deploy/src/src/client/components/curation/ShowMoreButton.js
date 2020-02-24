import React from 'react';
import './ShowMoreButton.scss';

const ShowMoreButton = props => (
  <a
    className="curation-show-more-button"
    style={{ fontWeight: '800' }}
    onClick={props.onClick}
  >
    {props.children}
  </a>
);

export default ShowMoreButton;
