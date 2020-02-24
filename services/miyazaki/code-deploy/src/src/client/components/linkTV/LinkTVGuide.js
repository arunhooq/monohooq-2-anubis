import React, { useState } from 'react';
import get from 'lodash.get';
import LinkTVSteps from './LinkTVSteps';
import './LinkTVGuide.scss';

const LinkTVGuide = ({ translation }) => {
  const [visible, setVisible] = useState(false);

  const linkTvGuide = get(translation, 'linkTv_guide', '');
  const linkTvGuideCodeToggle = get(translation, 'linkTv_guide_codeToggle', '');

  return (
    <div className="link-tv__guide">
      <h1>Link TV</h1>
      <p>{linkTvGuide}</p>
      <span onClick={() => setVisible(true)}>{linkTvGuideCodeToggle}</span>
      {visible && (
        <LinkTVSteps translation={translation} setVisible={setVisible} />
      )}
    </div>
  );
};

export default LinkTVGuide;
