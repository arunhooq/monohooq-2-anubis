import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../routes';
import { createPoster } from './shared/Poster';
import Header from './shared/Header';
import './collectionGallery.scss';

const mapStateToProps = state => ({
  collection: state.discover.collection,
  tracking: state.tracking,
  userSession: state.user.userSession
});

const renderTitleCard = (posterClick, titles, userLevel) => {
  return titles.map((title, index) => {
    const { availability, content_level: contentLevel } = title;
    const Poster = createPoster({
      label: {
        userLevel,
        contentLevel,
        availability,
        as: title.as
      }
    });

    const posterSrc = title.images.filter(image => image.type === 'POSTER')[0]
      .url;

    return (
      <div
        className="column is-4 poster-wrapper"
        onClick={() => posterClick(title)}
        key={index}
      >
        <Poster src={posterSrc} width="320" height="480" />
      </div>
    );
  });
};

class CollectionGallery extends React.Component {
  handlePoster = movie => {
    this.props.tracking.trackGtm('button_tap_contentdetails', {
      source_event_name: 'collection_page',
      id: movie.id,
      title: movie.title,
      as: movie.as
    });

    Router.pushRoute('detail', {
      movieId: movie.id
    });
  };

  render() {
    const { collection } = this.props;
    document.title = collection.name;

    return (
      <React.Fragment>
        <Header showBack>{collection.name}</Header>

        <div className="columns is-multiline is-mobile is-2 collection__cards">
          {renderTitleCard(
            this.handlePoster,
            collection.titles,
            this.props.userSession.user.userLevel
          )}
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(CollectionGallery);
