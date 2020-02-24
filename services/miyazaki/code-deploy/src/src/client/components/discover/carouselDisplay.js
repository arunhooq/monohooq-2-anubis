import React from 'react';
import NoSSR from 'react-no-ssr';
import Slider from 'react-slick';
import { Container } from 'react-bulma-components';
import hooqLoader from '../../static/img/hooq-loader.svg';
import { convertProtocolToHttps } from './../../util/common';
import { createPoster } from '../shared/Poster';
import RowTitle from '../shared/RowTitle';
import Img from 'react-image';

import 'slick-carousel/slick/slick.scss';
import 'slick-carousel/slick/slick-theme.scss';
import './carouselDisplay.scss';

const smallScreen = {
  centerMode: true,
  infinite: true,
  centerPadding: '30px',
  slidesToShow: 1,
  speed: 500,
  arrows: false,
  adaptiveHeight: false
};

const bigScreen = {
  slidesToShow: 3,
  slidesToScroll: 3,
  infinite: true,
  dots: false,
  adaptiveHeight: false
};

const settings = (bigScreen, smallScreen) => ({
  className: 'center',
  centerMode: true,
  adaptiveHeight: true,
  responsive: [
    {
      breakpoint: 1040,
      settings: bigScreen
    },
    {
      breakpoint: 1024,
      settings: bigScreen
    },
    {
      breakpoint: 600,
      settings: smallScreen
    },
    {
      breakpoint: 480,
      settings: smallScreen
    }
  ]
});

const ratio = {
  xxLarge: {
    x1: '&width=2500&height=977&scalingMode=aspectFill',
    x2: '&width=5000&height=1953&scalingMode=aspectFill',
    x3: '&width=7500&height=2930&scalingMode=aspectFill'
  },
  xLarge: {
    x1: '&width=1840&height=719&scalingMode=aspectFill',
    x2: '&width=3680&height=1438&scalingMode=aspectFill',
    x3: '&width=5520&height=2156&scalingMode=aspectFill'
  },
  large: {
    x1: '&width=1440&height=563&scalingMode=aspectFill',
    x2: '&width=2880&height=1125&scalingMode=aspectFill',
    x3: '&width=4320&height=1688&scalingMode=aspectFill'
  },
  medium: {
    x1: '&width=1240&height=484&scalingMode=aspectFill',
    x2: '&width=2480&height=969&scalingMode=aspectFill',
    x3: '&width=3720&height=1453&scalingMode=aspectFill'
  },
  small: {
    x1: '&width=1040&height=585&scalingMode=aspectFill',
    x2: '&width=2080&height=1170&scalingMode=aspectFill',
    x3: '&width=3120&height=1755&scalingMode=aspectFill'
  },
  xSmall: {
    x1: '&width=840&height=473&scalingMode=aspectFill',
    x2: '&width=1680&height=945&scalingMode=aspectFill',
    x3: '&width=2520&height=1418&scalingMode=aspectFill'
  },
  default: '&width=640&height=360&scalingMode=aspectFill'
};

const CarouselLoader = props => {
  return (
    <Container className="carousel__loader">
      <img src={hooqLoader} alt="hooq loader" />
    </Container>
  );
};

const placeholderImage = <img src="/static/img/spotlightimg_840x473.jpg" />;

export const CarouselPosterTitle = ({ spotlight, title }) => {
  if (spotlight === false) {
    return null;
  }

  return (
    <div className="carousel__title carousel__title--with-label">{title}</div>
  );
};

export const CarouselImage = props => {
  const { movie, onClick, spotlight, style } = props;
  const posterUrl = convertProtocolToHttps(movie.poster.url);
  const containerStyle = spotlight
    ? 'carousel__item'
    : 'carousel__item--landscape';

  return (
    <div
      className={containerStyle}
      onClick={() => onClick(movie)}
      style={style}
    >
      <picture>
        <source
          media="(min-width: 1840px)"
          srcSet={`${posterUrl + ratio.xxLarge.x1} 1x,
            ${posterUrl + ratio.xxLarge.x2} 2x,
            ${posterUrl + ratio.xxLarge.x3} 3x`}
        />
        <source
          media="(min-width: 1440px)"
          srcSet={`${posterUrl + ratio.xLarge.x1} 1x,
            ${posterUrl + ratio.xLarge.x2} 2x,
            ${posterUrl + ratio.xLarge.x3} 3x`}
        />
        <source
          media="(min-width: 1240px)"
          srcSet={`${posterUrl + ratio.large.x1} 1x,
            ${posterUrl + ratio.large.x2} 2x,
            ${posterUrl + ratio.large.x3} 3x`}
        />
        <source
          media="(min-width: 1040px)"
          srcSet={`${posterUrl + ratio.medium.x1} 1x,
            ${posterUrl + ratio.medium.x2} 2x,
            ${posterUrl + ratio.medium.x3} 3x`}
        />
        <source
          media="(min-width: 840px)"
          srcSet={`${posterUrl + ratio.small.x1} 1x,
            ${posterUrl + ratio.small.x2} 2x,
            ${posterUrl + ratio.small.x3} 3x`}
        />
        <source
          media="(min-width: 640px)"
          srcSet={`${posterUrl + ratio.xSmall.x1} 1x,
            ${posterUrl + ratio.xSmall.x2} 2x,
            ${posterUrl + ratio.xSmall.x3} 3x`}
        />

        <Img
          crossOrigin="anonymous"
          className="carousel__image"
          src={`${posterUrl + ratio.default}`}
          loader={placeholderImage}
          alt={movie.title}
          unloader={placeholderImage}
        />
      </picture>
      <CarouselPosterTitle spotlight={spotlight} title={movie.title} />
    </div>
  );
};

const CarouselDisplay = props => {
  const {
    row,
    onClick,
    seeAllClick,
    userLevel,
    imageType,
    withTitle,
    translation
  } = props;
  const mappedMovies =
    row !== undefined && row.hasOwnProperty('data')
      ? row.data.map(el => {
          return {
            id: el.id,
            as: el.as,
            season: 1,
            title: el.title,
            poster: el.images.filter(img => img.type === imageType)[0],
            content_level: el.content_level,
            availability: el.availability
          };
        })
      : [];
  const isSpotlight = withTitle ? false : true;

  return (
    <React.Fragment>
      {withTitle && (
        <div className="container">
          <RowTitle
            row={row}
            seeAllClick={seeAllClick}
            translation={translation}
          />
        </div>
      )}

      <Container
        style={{
          marginTop: withTitle ? '-1.7rem' : '-0.5rem',
          marginBottom: withTitle ? '-0.5rem' : '0'
        }}
      >
        <NoSSR onSSR={<CarouselLoader />}>
          <Slider {...settings(bigScreen, smallScreen)}>
            {mappedMovies.map((movie, index) => {
              if (movie.poster !== undefined && movie.poster.url !== null) {
                const { availability, content_level: contentLevel } = movie;
                const Poster = createPoster(
                  {
                    label: {
                      userLevel,
                      contentLevel,
                      availability,
                      as: movie.as,
                      spotlight: isSpotlight
                    }
                  },
                  CarouselImage
                );

                return (
                  <div className="carousel__wrapper" key={index}>
                    <Poster
                      movie={movie}
                      onClick={onClick}
                      key={index}
                      translation={translation}
                    />
                  </div>
                );
              }
            })}
          </Slider>
        </NoSSR>
      </Container>
    </React.Fragment>
  );
};

export default CarouselDisplay;
