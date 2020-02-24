import React from 'react';
import { Container, Heading, Columns } from 'react-bulma-components';
import { connect } from 'react-redux';

import './contentDetail.scss';

const mapStateToProps = state => ({
  detail: state.detail
});

class ContainDetail extends React.Component {
  render() {
    const { movie } = this.props.detail;
    document.title = movie.title;
    return (
      <React.Fragment>
        <Container style={{ paddingTop: '24px' }}>
          <Columns breakpoint="mobile">
            <Columns.Column size={12} className="column-10rem">
              <Heading className="is-5 movie-title">{movie.title}</Heading>
              <Heading subtitle className="is-6 movie-title">
                {movie.running_time_friendly}
                {movie.running_time_friendly && (
                  <span style={{ fontWeight: 'bolder' }} key="middot">
                    &nbsp;&middot;&nbsp;
                  </span>
                )}
                {movie.meta.ageRating}
                {movie.meta.ageRating && (
                  <span style={{ fontWeight: 'bolder' }} key="middot2">
                    &nbsp;&middot;&nbsp;
                  </span>
                )}
                {movie.meta.releaseYear}
              </Heading>
            </Columns.Column>
            <Columns.Column size={12} className="column-10rem">
              <div
                className="is-12 is-size-6-mobile has-text-left has-text-weight-bold"
                style={{ display: 'flex' }}
              >
                <img src="/static/img/ic-sound.svg" alt="language" />
                <span className="movie-attribute">
                  {movie.audios.join(', ')}
                </span>
              </div>
            </Columns.Column>
            {movie.languages.length > 0 && (
              <Columns.Column size={12} className="subtitle-column">
                <div
                  className="is-12 is-size-6-mobile has-text-left has-text-weight-bold"
                  style={{ display: 'flex' }}
                >
                  <img src="/static/img/ic-subtitles.svg" alt="subtitle" />
                  <span className="movie-attribute">
                    {movie.languages.join(', ')}
                  </span>
                </div>
              </Columns.Column>
            )}
          </Columns>
        </Container>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(ContainDetail);
