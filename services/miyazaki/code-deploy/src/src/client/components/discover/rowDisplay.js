import React from 'react';
import { Columns } from 'react-bulma-components';
import { createPoster } from './../shared/Poster';
import RowTitle from './../shared/RowTitle';
import ProgressCircle from './progressCircle';
import Oval from '../../static/img/oval.svg';
import './rowDisplay.scss';

const RenderTitle = (
  posterClick,
  movies,
  isContinueWatch = false,
  userLevel
) => {
  const customClass = isContinueWatch ? 'continue-watch-rail' : 'is-multiline';
  return (
    <div
      className={`columns ${customClass} is-mobile`}
      style={{
        marginBottom: '0.8rem'
      }}
    >
      {movies.map((movie, index) => {
        const {
          availability,
          contentLevel,
          percentage,
          timeLeft,
          season,
          episode
        } = movie;
        const Poster = createPoster({
          label: {
            userLevel,
            contentLevel,
            availability,
            as: movie.as
          }
        });

        return (
          <div className="column is-4 poster-wrapper" key={index}>
            {renderContentTitle(
              isContinueWatch,
              <a onClick={() => posterClick(movie)}>
                <Poster src={movie.poster.url} width="320" height="480" />
              </a>,
              percentage,
              timeLeft,
              season,
              episode
            )}
          </div>
        );
      })}
    </div>
  );
};

const ContinueWatchWrapper = props => {
  return (
    <div className="fullWidth continue-watch">
      <div className="continue-watch-wrapper">
        <div className="progress-ring">
          <ProgressCircle progress={props.percentage} />
        </div>
        {props.children}
      </div>
      <div className="watch-remain-wrapper">
        <img src={Oval} />
        <span className="watch-remain-text">
          {props.season
            ? `S${props.season} Ep.${props.episode}`
            : `${props.timeLeft}m left`}
        </span>
      </div>
    </div>
  );
};

const renderContentTitle = (
  isContinueWatch = false,
  component,
  percentage = 0,
  timeLeft = 0,
  season = null,
  episode = null
) => {
  if (!isContinueWatch) {
    return component;
  } else {
    return (
      <ContinueWatchWrapper
        percentage={percentage}
        timeLeft={timeLeft}
        season={season}
        episode={episode}
      >
        {component}
      </ContinueWatchWrapper>
    );
  }
};

const CollectionTitle = props => {
  return <span className="discover-collection__title">{props.children}</span>;
};

const movieMapper = (movie, userLevel) => {
  const img =
    movie.hasOwnProperty('images') && movie.images.length > 0
      ? movie.images.filter(i => {
          return i.type === 'POSTER';
        })[0]
      : { url: null };

  const {
    id,
    title,
    as,
    percentage,
    timeLeft,
    availability,
    content_level,
    lastWatched
  } = movie;
  return {
    id,
    title,
    as,
    availability,
    content_level,
    poster: img,
    percentage,
    timeLeft,
    userLevel,
    contentLevel: content_level,
    availability,
    lastWatched
  };
};

const RowDisplay = props => {
  const { row, translation, userLevel } = props;
  const collectionId = row.obj_id;
  const movies =
    row.data !== null
      ? row.data.map(movie => movieMapper(movie, userLevel))
      : [];

  return (
    <React.Fragment>
      {collectionId !== null && (
        <div className="container">
          <RowTitle
            row={row}
            seeAllClick={props.seeAllClick}
            translation={translation}
          />

          {RenderTitle(
            props.posterClick,
            movies.slice(0, 9),
            false,
            props.userLevel
          )}
        </div>
      )}
    </React.Fragment>
  );
};

const RowContinueWatch = props => {
  const { movies, userLevel } = props;
  const mappedMovies = movies.map(movie => movieMapper(movie, userLevel));
  return (
    <React.Fragment>
      <div
        className="container"
        style={{
          paddingLeft: '.15rem',
          paddingRight: '.15rem',
          marginTop: '1rem'
        }}
      >
        <Columns breakpoint="mobile">
          <Columns.Column
            size={6}
            style={{
              paddingLeft: '.20rem'
            }}
          >
            <CollectionTitle>{props.rowName}</CollectionTitle>
          </Columns.Column>
        </Columns>
        {RenderTitle(props.posterClick, mappedMovies, true)}
      </div>
    </React.Fragment>
  );
};

export { RenderTitle, CollectionTitle, RowDisplay, RowContinueWatch };
