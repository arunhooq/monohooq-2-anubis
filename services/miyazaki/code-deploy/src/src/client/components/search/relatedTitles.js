import React from 'react';
import { connect } from 'react-redux';
import Highlight from 'react-highlighter';
import { setRelatedSearchResult } from '../../actions/searchAction';
import { MOVIE_SEARCH } from '../../constants/search';
import { Router } from '../../routes';

const mapStateToProps = state => ({
  search: state.search,
  region: state.discover.region
});

const mapDispatchToProps = dispatch => ({
  setRelatedSearchResult: (title, movies) =>
    dispatch(setRelatedSearchResult({ title, movies }))
});

const RelatedItem = props => {
  const { title, keyword } = props;
  return (
    <div
      className="related-box marTop-1rem"
      onClick={() => props.onClick(title)}
    >
      <span className="related-text">
        <Highlight
          search={keyword}
          matchClass="search-match"
          ignoreDiacritics={true}
        >
          {title.title}
        </Highlight>
      </span>
      <img
        src="/static/img/ic-arrow-left.svg"
        width="16px"
        style={{ float: 'right' }}
      />
    </div>
  );
};

class RelatedTitles extends React.Component {
  searchRelated = async title => {
    Router.pushRoute('relatedSearch', {
      q: title.id,
      label: title.title,
      type: MOVIE_SEARCH
    });
  };

  render() {
    const { relatedTitles, keyword } = this.props.search;
    const show = relatedTitles.length > 0;
    return (
      <React.Fragment>
        {show && (
          <div className="container padTop-1rem">
            <span className="search-movie-title">
              Search result related to "{keyword}"
            </span>
            <div>
              {relatedTitles.map((el, index) => {
                return (
                  <RelatedItem
                    key={index}
                    title={el}
                    keyword={keyword}
                    onClick={this.searchRelated}
                  />
                );
              })}
            </div>
          </div>
        )}
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RelatedTitles);
