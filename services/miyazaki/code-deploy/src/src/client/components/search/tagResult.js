import Highlight from 'react-highlighter';

import './tagResult.scss';

export default props => {
  return (
    <span
      onClick={props.onClick}
      className="tag search-tag has-background-white"
    >
      <Highlight
        search={props.keyword}
        matchClass="search-match"
        ignoreDiacritics={true}
      >
        {props.label}
      </Highlight>
    </span>
  );
};
