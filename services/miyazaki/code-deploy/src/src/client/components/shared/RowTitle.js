import React from 'react';
import './RowTitle.scss';

const RowTitle = props => {
  const { row, seeAllClick, translation } = props;

  return (
    <div className="row-title">
      <div className="row-title__title">
        <div className="row-title__text">{row.row_name}</div>
      </div>

      <div className="row-title__see-all">
        <a onClick={() => seeAllClick(row)}>{translation.curation_seeAll}</a>
      </div>
    </div>
  );
};

export default RowTitle;
