import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import { createPoster } from './../shared/Poster';
import { convertProtocolToHttps } from './../../util/common';
import './relatedMovies.scss';

const mapStateToProps = state => ({
  detail: state.detail,
  translation: state.translation,
  tracking: state.tracking,
  userSession: state.user.userSession
});

const getPoster = title => {
  const filteredImage = title.images.filter(image => image.type === 'POSTER');
  const [poster] = filteredImage;
  return convertProtocolToHttps(poster.url);
};

const renderTitleCard = (posterClick, titles, userSession) => {
  const { userLevel } = userSession.user;
  return titles.map((title, index) => {
    const Poster = createPoster({
      label: {
        userLevel,
        contentLevel: title.content_level,
        availability: title.availability,
        as: title.as
      }
    });

    return (
      <div
        className="column is-4 poster-wrapper"
        key={index}
        onClick={() => posterClick(title)}
      >
        <Poster
          src={`${getPoster(
            title
          )}&scalingMode=aspectFill&width=320&height=480`}
          width="320"
          height="480"
        />
      </div>
    );
  });
};

export class RelatedMovies extends React.Component {
  filterPoster = images => {
    const image = images.filter(img => img.type === 'POSTER')[0];
    return image.url;
  };

  handlePoster = movie => {
    this.props.tracking.trackGtm('button_tap_contentdetails', {
      id: movie.id,
      title: movie.title,
      as: movie.as,
      source_event_name: `${this.props.page}_page`
    });

    Router.pushRoute('detail', {
      movieId: movie.id
    });
  };

  render() {
    const { userSession } = this.props;
    const { relatedMovies } = this.props.detail;
    const { contentDetail_similarTitles } = this.props.translation;

    return (
      <React.Fragment>
        {relatedMovies.length > 0 && (
          <div className="related-movie__title">
            <span className="is-size-6-mobile has-text-left has-text-weight-bold">
              {contentDetail_similarTitles.replace(': {0}', '')}
            </span>
          </div>
        )}

        <div className="columns is-multiline is-mobile is-2 related-movie__cards">
          {renderTitleCard(this.handlePoster, relatedMovies, userSession)}
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(RelatedMovies);
