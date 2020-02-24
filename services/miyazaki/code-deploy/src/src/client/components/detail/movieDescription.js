import React from 'react';
import { connect } from 'react-redux';
import { Container, Columns, Button } from 'react-bulma-components';
import Content from 'react-bulma-components/lib/components/content';
import './movieDescription.scss';

const mapStateToProps = state => ({
  detail: state.detail,
  translation: state.translation
});

class MovieDescription extends React.Component {
  render() {
    const { movie } = this.props.detail;
    const { translation } = this.props;

    const directors = movie.people.filter(person => person.role === 'DIRECTOR');
    const director = directors.length ? directors[0].name : '';
    const people = movie.people
      .filter(person => person.role === 'CAST')
      .map(el => el.name)
      .join(', ');
    const categories = movie.tags.map(el => el.label).join(', ');
    return (
      <React.Fragment>
        <Container>
          <Columns breakpoint="mobile" className="movie-description">
            <Columns.Column size={12}>
              <Content className="description">{movie.description}</Content>
            </Columns.Column>

            {/* director section  */}
            <Columns.Column size={3}>
              <div className="is-size-6-mobile has-text-left has-text-weight-bold">
                {translation.contentDetail_director}
              </div>
            </Columns.Column>
            <Columns.Column size={9}>
              <div className="is-size-6-mobile has-text-left">{director}</div>
            </Columns.Column>

            {/* category section */}
            <Columns.Column size={3}>
              <div className="is-size-6-mobile has-text-left has-text-weight-bold">
                {translation.contentDetail_categories}
              </div>
            </Columns.Column>
            <Columns.Column size={9}>
              <div className="is-size-6-mobile has-text-left">{categories}</div>
            </Columns.Column>

            {/* actor section */}
            <Columns.Column size={3}>
              <div className="is-size-6-mobile has-text-left has-text-weight-bold">
                {translation.contentDetail_cast}
              </div>
            </Columns.Column>
            <Columns.Column size={9}>
              <div className="is-size-6-mobile has-text-left">{people}</div>
            </Columns.Column>
          </Columns>
        </Container>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(MovieDescription);
