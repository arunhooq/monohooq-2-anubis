import { connect } from 'react-redux';
import './CurationHeader.scss';

const mapStateToProps = state => ({
  discover: state.discover,
  tracking: state.tracking
});

const Title = props => (
  <span className="curation-header__title">{props.children}</span>
);

const SeeAllLink = props => (
  <a className="curation-header__see-all-link" onClick={props.seeAllClick}>
    {props.children}
  </a>
);

const CurationHeader = ({ seeAllClick, curation, translation }) => {
  return (
    <div className="curation-header">
      <Title>{curation.row_name}</Title>
      <SeeAllLink seeAllClick={() => seeAllClick(curation)}>
        {translation.curation_seeAll}
      </SeeAllLink>
    </div>
  );
};

export default connect(
  mapStateToProps,
  null
)(CurationHeader);
