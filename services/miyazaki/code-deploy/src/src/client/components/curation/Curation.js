import React from 'react';
import CurationWrapper from './CurationWrapper';

const Curation = props => (
  <React.Fragment>
    {props.curations.map((curation, index) => {
      return (
        curation.row_name &&
        curation.data.length > 0 && (
          <CurationWrapper curation={curation} page={props.page} key={index} />
        )
      );
    })}
  </React.Fragment>
);

export default Curation;
