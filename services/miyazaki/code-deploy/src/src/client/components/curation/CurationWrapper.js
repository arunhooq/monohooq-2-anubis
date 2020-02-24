import React from 'react';
import { connect } from 'react-redux';
import { Router } from '../../routes';
import { createPoster } from './../shared/Poster';
import RowTitle from './../shared/RowTitle';
import ShowMoreButton from './ShowMoreButton';
import IconShowMore from './../icons/IconShowMore';
import { toTitleCase } from '../../helpers/strUtils';
import './CurationWrapper.scss';

const MAX_DISPLAYED_TITLES_PER_CONTAINER = 9;
const MAX_DISPLAYED_TITLES_PER_CONTAINER_ON_INITIAL_LOAD = 6;

const mapStateToProps = state => ({
  tracking: state.tracking,
  userSession: state.user.userSession,
  translation: state.translation
});

const CuratedTitles = props => {
  const { userLevel } = props;
  return (
    <div
      className="columns is-multiline is-mobile is-variable is-1-mobile"
      style={{ paddingTop: '0.6rem' }}
    >
      {props.displayedTitles.map((movie, index) => {
        const { availability, content_level: contentLevel } = movie;
        const Poster = createPoster({
          label: {
            userLevel,
            contentLevel,
            availability,
            as: movie.as
          }
        });
        const posterSrc = movie.images.filter(
          image => image.type === 'POSTER'
        )[0].url;

        return (
          <div
            onClick={() => props.posterClick(movie)}
            className="column is-4 is-narrow-mobile"
            key={index}
            style={{ paddingTop: '0.25rem', paddingBottom: '0.25rem' }}
          >
            <Poster src={posterSrc} width="320" height="480" />
          </div>
        );
      })}
    </div>
  );
};

const PaginationControl = ({
  displayedTitles,
  totalTitles,
  curation,
  showMore,
  seeAllClick,
  translation
}) => {
  const renderPaginationControl = curation => {
    const shouldDisplayShowAll =
      displayedTitles.length === MAX_DISPLAYED_TITLES_PER_CONTAINER;
    const shouldHideShowMore =
      displayedTitles.length >
        MAX_DISPLAYED_TITLES_PER_CONTAINER_ON_INITIAL_LOAD ||
      totalTitles < MAX_DISPLAYED_TITLES_PER_CONTAINER_ON_INITIAL_LOAD;

    if (shouldDisplayShowAll) {
      return (
        <div
          onClick={() => seeAllClick(curation)}
          className="columns is-mobile is-vcentered is-centered is-variable is-1-mobile"
        >
          <div className="column is-narrow" style={{ marginBottom: '-1rem' }}>
            <IconShowMore
              style={{
                paddingTop: '0.1rem',
                marginRight: '0.4rem',
                stroke: '#951b81',
                strokeWidth: '2'
              }}
            />
            <ShowMoreButton>
              {toTitleCase(translation.curation_seeAll)}
            </ShowMoreButton>
          </div>
        </div>
      );
    }

    if (shouldHideShowMore) {
      return null;
    }

    return (
      <div
        onClick={() => showMore(curation)}
        className="columns is-mobile is-vcentered is-centered is-variable is-1-mobile"
      >
        <div className="column is-narrow" style={{ marginBottom: '-1rem' }}>
          <IconShowMore
            style={{
              paddingTop: '0.1rem',
              marginRight: '0.4rem',
              stroke: '#951b81',
              strokeWidth: '2'
            }}
          />
          <ShowMoreButton>{translation.curation_showMore}</ShowMoreButton>
        </div>
      </div>
    );
  };

  return <React.Fragment>{renderPaginationControl(curation)}</React.Fragment>;
};

class CurationWrapper extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayedTitles: [],
      totalTitles: 0
    };

    this.handleShowMore = this.handleShowMore.bind(this);
    this.handleSeeAll = this.handleSeeAll.bind(this);
  }

  componentDidMount() {
    this.setState({
      displayedTitles: this.props.curation.data.slice(
        0,
        MAX_DISPLAYED_TITLES_PER_CONTAINER_ON_INITIAL_LOAD
      ),
      totalTitles: this.props.curation.data.length
    });
  }

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

  handleShowMore = collection => {
    this.props.tracking.trackGtm('button_tap_expand_collection', {
      obj_id: collection.obj_id,
      row_id: collection.row_id,
      row_name: collection.row_name,
      source_event_name: `${this.props.page}_page`
    });
    this.setState({
      displayedTitles: this.props.curation.data.slice(
        0,
        MAX_DISPLAYED_TITLES_PER_CONTAINER
      )
    });
  };

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

  render() {
    const { translation } = this.props;
    return (
      <div className="curation-wrapper">
        <div className="curation-header">
          <RowTitle
            seeAllClick={this.handleSeeAll}
            row={this.props.curation}
            translation={translation}
          />
        </div>
        <CuratedTitles
          userLevel={this.props.userSession.user.userLevel}
          posterClick={this.handlePoster}
          displayedTitles={this.state.displayedTitles}
          translation={translation}
        />
        <PaginationControl
          showMore={this.handleShowMore}
          seeAllClick={this.handleSeeAll}
          curation={this.props.curation}
          displayedTitles={this.state.displayedTitles}
          totalTitles={this.state.totalTitles}
          translation={translation}
        />
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(CurationWrapper);
