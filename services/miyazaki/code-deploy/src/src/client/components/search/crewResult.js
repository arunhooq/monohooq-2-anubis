import React from 'react';
import { connect } from 'react-redux';
import TagResult from './tagResult';
import { CREW_SEARCH } from '../../constants/search';
import { Router } from '../../routes';

const mapStateToProps = state => ({
  search: state.search
});

class CrewResult extends React.Component {
  searchRelated = async crew => {
    Router.pushRoute('relatedSearch', {
      q: crew.id,
      label: crew.name,
      type: CREW_SEARCH
    });
  };

  render() {
    const { crews, keyword } = this.props.search;
    const show = crews.length > 0;
    return (
      <React.Fragment>
        {show && (
          <div className="container padTop-1rem">
            <span className="search-movie-title">
              Cast & Crew ({crews.length})
            </span>
            <div className="tags are-medium padTop-05rem">
              {crews.map((crew, index) => (
                <TagResult
                  key={index}
                  label={crew.name}
                  keyword={keyword}
                  onClick={() => this.searchRelated(crew)}
                />
              ))}
            </div>
          </div>
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(CrewResult);
