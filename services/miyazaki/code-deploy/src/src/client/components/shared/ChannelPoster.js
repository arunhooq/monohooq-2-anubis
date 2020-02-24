import React from 'react';
import LazyLoad from 'react-lazyload';
import compose from 'lodash.flowright';
import isEmpty from 'lodash.isempty';
import PropTypes from 'prop-types';
import { convertProtocolToHttps } from './../../util/common';
import { get } from 'lodash';
import './ChannelPoster.scss';
import Img from 'react-image';
import { USER_LEVELS } from './../../constants/common';

const labels = [
  {
    name: 'free',
    roles: [USER_LEVELS.VISITOR],
    isPremium: [false],
    style: {
      rules: ['channel-poster-container__background-free-label'],
      icon: {}
    }
  },
  {
    name: 'vip',
    roles: [USER_LEVELS.VISITOR, USER_LEVELS.REGISTERED],
    isPremium: [true],
    style: {
      rules: ['channel-poster-container__background-vip-label'],
      icon: {
        src: '/static/img/vip.svg'
      }
    }
  },
  {
    name: 'no-label',
    roles: [USER_LEVELS.SUBSCRIBER],
    isPremium: [true, false],
    style: {
      rules: ['no-label'],
      icon: {}
    }
  }
];

const backgroundPosterStyles = [
  {
    roles: [USER_LEVELS.SUBSCRIBER],
    isPremium: [true, false],
    style: {
      borderRadius: '4px',
      height: 'auto',
      boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
    },
    className: 'channel-poster-container--all-rounded'
  },
  {
    roles: [USER_LEVELS.VISITOR, USER_LEVELS.REGISTERED],
    isPremium: [true, false],
    style: {
      borderTopLeftRadius: '4px',
      borderTopRightRadius: '4px',
      height: 'auto',
      boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
    },
    className: 'channel-poster-container--top-rounded'
  }
];

export const calculateBackgroundPosterStyle = (userLevel, isPremium) => {
  const [filteredStyle] = backgroundPosterStyles.filter(item => {
    return item.roles.includes(userLevel) && item.isPremium.includes(isPremium);
  });

  return filteredStyle.style;
};

export const calculateChannelPosterContainerClassName = (
  userLevel,
  isPremium
) => {
  const [filteredStyle] = backgroundPosterStyles.filter(item => {
    return item.roles.includes(userLevel) && item.isPremium.includes(isPremium);
  });

  return filteredStyle.className;
};

export const calculateLabel = (userLevel, isPremium) => {
  const filteredLabels = labels.filter(
    label =>
      label.roles.includes(userLevel) && label.isPremium.includes(isPremium)
  );

  if (filteredLabels.length > 0) {
    return filteredLabels[0];
  }

  return null;
};

export const createLabel = options => {
  const calculatedLabel = calculateLabel(options.userLevel, options.isPremium);

  if (calculatedLabel !== null && calculatedLabel.name === 'no-label') {
    return null;
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
            calculatedLabel.name
          ) : (
            <img src={calculatedLabel.style.icon.src} />
          )}
        </h5>
      </div>
    );
  }

  return (
    <div className="channel-poster-container__background-free-label">
      <h5
        className="has-text-centered is-size-7 label-text"
        style={{
          textTransform: 'uppercase'
        }}
      >
        free
      </h5>
    </div>
  );
};

const PosterImageContainer = props => {
  const { images, channel, userLevel: userAccountLevel } = props;
  const backgroundUrl = get(images, 'backgroundUrl', '');
  const transparentLogoUrl = get(images, 'transparentLogoUrl', '');
  const placeholderImage = (
    <img src="/static/img/noimage@2x.png" style={{ height: '4.875em' }} />
  );

  const backgroundImgStyle = calculateBackgroundPosterStyle(
    userAccountLevel,
    channel.isPremium
  );

  return (
    <React.Fragment>
      <Img
        src={convertProtocolToHttps(backgroundUrl)}
        loader={placeholderImage}
        unloader={placeholderImage}
        style={backgroundImgStyle}
      />
      <Img
        className="channel-poster-transparent-logo"
        src={convertProtocolToHttps(transparentLogoUrl)}
      />
    </React.Fragment>
  );
};

const withLabel = options => {
  return WrappedComponent => {
    class WithLabel extends React.Component {
      render() {
        const radiusForNoLabel = {
          borderRadius: '4px',
          boxShadow: '1px 1px 0 0 rgba(0, 0, 0, 0.15)'
        };

        const additionalProps = {};

        const createdLabel = createLabel(options);
        if (
          createdLabel !== null &&
          createdLabel.props.className === 'no-label'
        ) {
          additionalProps.style = radiusForNoLabel;
        }

        const injectedProps = {
          ...this.props,
          ...additionalProps
        };

        return (
          <React.Fragment>
            <WrappedComponent {...injectedProps} />
            {createdLabel}
          </React.Fragment>
        );
      }
    }
    return WithLabel;
  };
};

export const createPoster = function(options = {}) {
  let enhance;
  const { label } = options;

  if (label !== undefined) {
    enhance = compose(withLabel(label));
    return enhance(BasePoster);
  }

  return BasePoster;
};

const BasePoster = props => {
  const { channel, userLevel: userAccountLevel } = props;

  const additionalClass = calculateChannelPosterContainerClassName(
    userAccountLevel,
    channel.isPremium
  );

  return (
    <LazyLoad height="100%" width="100%" offset={200} once>
      <div className={`image ${additionalClass}`}>
        <PosterImageContainer {...props} />
      </div>
    </LazyLoad>
  );
};

BasePoster.propTypes = {
  channel: PropTypes.object.isRequired,
  userLevel: PropTypes.number.isRequired
};

export default BasePoster;
