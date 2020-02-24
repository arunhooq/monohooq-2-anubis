import React from 'react';
import get from 'lodash.get';
import './LinkTVSteps.scss';

const LinkTVSteps = ({ setVisible, translation }) => {
  const linkTv_steps = get(translation, 'linkTv_steps', '');
  const linkTv_steps_closeButton = get(
    translation,
    'linkTv_steps_closeButton',
    ''
  );

  return (
    <div className="link-tv__steps">
      <ol>
        {linkTv_steps.split(',\n').map((el, index) => {
          return (
            <li
              key={index}
              dangerouslySetInnerHTML={{
                __html: el.replace("'", '').trim()
              }}
            />
          );
        })}
      </ol>

      <span
        className="link-tv__steps__button"
        onClick={() => setVisible(false)}
      >
        {linkTv_steps_closeButton}
      </span>
    </div>
  );
};

export default LinkTVSteps;
