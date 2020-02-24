import React, { Component } from 'react';
import { connect } from 'react-redux';
import CollectionGallery from '../components/collectionGallery';
import { setTitlesCollection } from '../actions/discoverAction';
import { setLoadingState } from '../actions/loadingAction';
import { setVisibility } from '../actions/menuAction';
import { getCollectionTitlesData, getCollectionData } from '../util/collection';
import { redirectErrorPage } from '../helpers/redirect';
import { setInitialProps } from '../util/pages';
import PageLoadTracker from '../helpers/tracking/pageLoadTracker';
import ClientError from './../../common/ClientError';
import { COLLECTION_NOT_FOUND } from './../../common/ErrorCodes';

const mapStateToProps = (state, ownProps) => {
  return {
    loading: state.loading,
    userSession: state.user.userSession
  };
};

const mapDispatchToProps = dispatch => ({
  setLoadingState: isLoading => dispatch(setLoadingState(isLoading)),
  setVisibility: visible => dispatch(setVisibility(visible)),
  setTitlesCollection: collection => dispatch(setTitlesCollection(collection))
});

class Collection extends Component {
  static pageTransitionDelayEnter = true;

  static async getInitialProps(ctx) {
    const { query, asPath } = ctx;
    const { collectionId } = query;
    await setInitialProps(ctx);

    return { collectionId, asPath };
  }

  constructor(props) {
    super(props);
  }

  componentWillMount() {
    this.props.setVisibility(false);
  }

  async componentDidMount() {
    await this.setInitialStore();

    this.timeoutId = setTimeout(() => {
      this.props.pageTransitionReadyToEnter();
      this.props.setLoadingState(false);
    }, 400);
  }

  componentWillUnmount() {
    if (this.timeoutId) clearTimeout(this.timeoutId);
  }

  async setInitialStore() {
    const { collectionId, asPath } = this.props;
    const titles = await getCollectionTitlesData(collectionId, 1, 25);
    const name = await getCollectionData(collectionId);

    if (titles.length === 0) {
      return redirectErrorPage(
        false,
        null,
        {
          tabMenu: 'false',
          back: 'true',
          retryUrl: asPath
        },
        new ClientError(
          COLLECTION_NOT_FOUND.errorCode,
          COLLECTION_NOT_FOUND.message
        )
      );
    }

    this.props.setTitlesCollection({ name, titles });
  }

  render() {
    const { userSession, loading, name, collectionId } = this.props;
    if (loading.isLoading) return null;

    const payload = { name, collectionId };

    return (
      <React.Fragment>
        <PageLoadTracker
          trackingTag="collection"
          payload={payload}
          userSession={userSession}
        />
        <div className="section" style={{ padding: '0' }}>
          <div className="container">
            <CollectionGallery />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

Collection.propTypes = {};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Collection);
