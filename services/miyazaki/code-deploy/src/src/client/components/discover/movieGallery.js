import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import get from 'lodash.get';
import CarouselDisplay from './carouselDisplay';
import { RowDisplay } from './rowDisplay';
import { BACKGROUND } from '../../constants/movie';

const mapStateToProps = state => ({
  discover: state.discover,
  tracking: state.tracking,
  userSession: state.user.userSession,
  translation: state.translation
});

class MovieGallery extends React.Component {
  handleSeeAll = collection => {
    this.props.tracking.trackGtm('button_tap_see_all_collection', {
      obj_id: collection.obj_id,
      row_id: collection.row_id,
      row_name: collection.row_name,
      source_event_name: `${this.props.page}_page`
    });

    Router.pushRoute('collection', {
      collectionId: collection.obj_id
    });
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
    const { userSession, row, translation } = this.props;
    const isLandscape = get(row, 'meta.imageOrientation', '') === 'Landscape';

    if (row.row_name && row.data.length > 0) {
      return isLandscape ? (
        <CarouselDisplay
          row={row}
          onClick={this.handlePoster}
          seeAllClick={this.handleSeeAll}
          subscription={userSession.subscription}
          userLevel={userSession.user.userLevel}
          imageType={BACKGROUND}
          withTitle={true}
          translation={translation}
        />
      ) : (
        <RowDisplay
          row={row}
          posterClick={this.handlePoster}
          seeAllClick={this.handleSeeAll}
          subscription={userSession.subscription}
          userLevel={userSession.user.userLevel}
          translation={translation}
        />
      );
    }

    return null;
  }
}

export default connect(
  mapStateToProps,
  null
)(MovieGallery);
