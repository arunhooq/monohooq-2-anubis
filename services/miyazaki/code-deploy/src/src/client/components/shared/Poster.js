import React from 'react';
import { connect } from 'react-redux';
import LazyLoad from 'react-lazyload';
import compose from 'lodash.flowright';
import get from 'lodash.get';
import isEmpty from 'lodash.isempty';
import Img from 'react-image';
import { convertProtocolToHttps } from './../../util/common';
import { SVOD, TVOD, TVSHOW, MOVIE } from '../../constants/movie';
import './Poster.scss';
import { USER_LEVELS } from './../../constants/common';

const labels = [
  // content level 10
  {
    availability: SVOD,
    name: 'free',
    userLevel: USER_LEVELS.VISITOR,
    contentLevel: 10,
    style: {
      rules: ['background-free-label'],
      icon: {}
    }
  },
  {
    availability: SVOD,
    name: 'free',
    userLevel: USER_LEVELS.REGISTERED,
    contentLevel: 10,
    style: {
      rules: ['background-free-label'],
      icon: {}
    }
  },
  {
    availability: SVOD,
    name: 'no-label',
    userLevel: USER_LEVELS.SUBSCRIBER,
    contentLevel: 10,
    style: {
      rules: ['no-label'],
      icon: {}
    }
  },
  // content level 20
  {
    availability: SVOD,
    name: 'vip',
    userLevel: USER_LEVELS.VISITOR,
    contentLevel: 20,
    style: {
      rules: ['background-vip-label'],
      icon: {
        src: '/static/img/vip.svg'
      }
    }
  },
  {
    availability: SVOD,
    name: 'free',
    userLevel: USER_LEVELS.REGISTERED,
    contentLevel: 20,
    style: {
      rules: ['background-free-label'],
      icon: {}
    }
  },
  {
    availability: SVOD,
    name: 'no-label',
    userLevel: USER_LEVELS.SUBSCRIBER,
    contentLevel: 20,
    style: {
      rules: ['no-label'],
      icon: {}
    }
  },
  // content level 30
  {
    availability: SVOD,
    name: 'vip',
    userLevel: USER_LEVELS.VISITOR,
    contentLevel: 30,
    style: {
      rules: ['background-vip-label'],
      icon: {
        src: '/static/img/vip.svg'
      }
    }
  },
  {
    availability: SVOD,
    name: 'vip',
    userLevel: USER_LEVELS.REGISTERED,
    contentLevel: 30,
    style: {
      rules: ['background-vip-label'],
      icon: {
        src: '/static/img/vip.svg'
      }
    }
  },
  {
    availability: SVOD,
    name: 'no-label',
    userLevel: USER_LEVELS.SUBSCRIBER,
    contentLevel: 30,
    style: {
      rules: ['no-label'],
      icon: {}
    }
  },
  // TVOD
  {
    availability: TVOD,
    name: 'rent',
    userLevel: USER_LEVELS.VISITOR,
    contentLevel: 10,
    style: {
      rules: ['background-rent-label'],
      icon: {}
    }
  },
  {
    availability: TVOD,
    name: 'rent',
    userLevel: USER_LEVELS.REGISTERED,
    contentLevel: 20,
    style: {
      rules: ['background-rent-label'],
      icon: {}
    }
  },
  {
    availability: TVOD,
    name: 'rent',
    userLevel: USER_LEVELS.SUBSCRIBER,
    contentLevel: 30,
    style: {
      rules: ['background-rent-label'],
      icon: {}
    }
  },
  {
    name: 'spotlight',
    style: {
      rules: ['background-spotlight-label'],
      icon: {}
    }
  }
];

export const calculateLabel = ({
  userLevel,
  contentLevel,
  availability,
  spotlight
}) => {
  const filteredLabels = labels.filter(label => {
    if (spotlight) {
      return label.name === 'spotlight' ? true : false;
    }

    if (
      label.userLevel === userLevel &&
      label.contentLevel === contentLevel &&
      label.availability === availability
    ) {
      return true;
    }

    return false;
  });

  if (filteredLabels.length > 0) {
    return filteredLabels[0];
  }

  return null;
};

export const createLabel = (options, translation) => {
  const calculatedLabel = calculateLabel({ ...options });

  if (options.text === undefined) {
    if (
      options.as === MOVIE &&
      options.contentLevel === 10 &&
      (options.userLevel >= 10 && options.userLevel <= 20)
    ) {
      options.text = 'free';
    }

    if (
      options.as === TVSHOW &&
      options.contentLevel === 10 &&
      (options.userLevel >= 10 && options.userLevel <= 20)
    ) {
      options.text = 'watch ep 1';
    }

    if (
      options.as === MOVIE &&
      options.contentLevel === 20 &&
      options.userLevel === 20
    ) {
      options.text = 'free';
    }

    if (
      options.as === TVSHOW &&
      options.contentLevel === 20 &&
      options.userLevel === 20
    ) {
      options.text = 'watch ep 1';
    }

    if (
      options.as === TVSHOW &&
      options.contentLevel === 20 &&
      options.userLevel === 10
    ) {
      options.text = 'vip';
    }

    if (options.contentLevel === 20 && options.userLevel === 10) {
      options.text = 'vip';
    }

    if (
      options.contentLevel === 30 &&
      (options.userLevel === 10 || options.userLevel === 20)
    ) {
      options.text = 'vip';
    }

    if (options.spotlight) {
      if (options.availability === TVOD) {
        options.text = get(
          translation,
          `spotlight_featuredPremiumPlus`,
          'Featured Premium+'
        );
      }

      if (options.as === MOVIE) {
        options.text = get(
          translation,
          `spotlight_featuredMovie`,
          'Featured Movie'
        );
      }

      if (options.as === TVSHOW) {
        options.text = get(
          translation,
          `spotlight_featuredTvShow`,
          'Featured TV Show'
        );
      }
    }
  }

  if (calculatedLabel !== null) {
    return (
      <div className={calculatedLabel.style.rules[0]}>
        <h5
          className="has-text-centered is-size-7 label-text"
          style={{
            textTransform: 'uppercase'
          }}
        >
          {isEmpty(calculatedLabel.style.icon) ? (
            options.text
          ) : (
            <img src={calculatedLabel.style.icon.src} />
          )}
        </h5>
      </div>
    );
  }

  return <React.Fragment />;
};

export const withLabel = options => {
  const { spotlight } = options;

  return WrappedComponent => {
    class WithLabel extends React.Component {
      render() {
        const { translation } = this.props;

        const radiusForNoLabel = {
          borderRadius: '4px',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        };

        const additionalProps = {
          style: {
            borderTopLeftRadius: '4px',
            borderTopRightRadius: '4px',
            boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
          }
        };

        let hasLabel = true;
        const createdLabel = createLabel(options, translation);
        if (createdLabel.props.className === 'no-label') {
          additionalProps.style = radiusForNoLabel;
          hasLabel = false;
        }
        const injectedProps = {
          ...this.props,
          ...additionalProps,
          spotlight,
          hasLabel
        };

        return (
          <div
            className="poster-label-wrapper"
            style={{ minHeight: `${this.props.height - 323}px` }}
          >
            <WrappedComponent {...injectedProps} />
            {createdLabel}
          </div>
        );
      }
    }
    return WithLabel;
  };
};

export const createPoster = (options, WrappedComponent) => {
  const Poster = WrappedComponent ? WrappedComponent : BasePoster;
  let enhance;

  if (options.label) {
    enhance = compose(withLabel(options.label));
    return enhance(Poster);
  }

  return BasePoster;
};

export const resizeUrl = (posterUrl, width, height) => {
  return `${posterUrl}&scalingMode=aspectFill&width=${width}&height=${height}`;
};

const renderImage = props => {
  const { src } = props;
  let resizedPosterUrl = '';
  if (src) {
    resizedPosterUrl = resizeUrl(
      convertProtocolToHttps(src),
      props.width,
      props.height
    );
  }

  const placeholderImage = <img src="/static/img/noimage@2x.png" />;

  return (
    <Img
      src={resizedPosterUrl}
      loader={placeholderImage}
      unloader={placeholderImage}
      style={props.style}
    />
  );
};

const BasePoster = props => (
  <LazyLoad height="100%" width="100%" offset={200} once>
    <figure className="image poster-image">{renderImage(props)}</figure>
  </LazyLoad>
);

export default BasePoster;
