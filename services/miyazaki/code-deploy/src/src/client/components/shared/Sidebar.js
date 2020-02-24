import React from 'react';
import getConfig from 'next/config';
import './Sidebar.scss';
import { connect } from 'react-redux';
import get from 'lodash.get';
import { Router } from '../../routes';
import { setSidebar, setVisibility } from '../../actions/menuAction';
import { setCommonNotification } from '../../actions/pagesAction';
import { CopyToClipboard } from 'react-copy-to-clipboard';
import { getDetails as getSubscriptionDetails } from '../../services/subscription';
import { setUserSubscription } from '../../actions/userAction';
const { publicRuntimeConfig } = getConfig();

let { INTERVAL_TO_SHOW } = publicRuntimeConfig;
INTERVAL_TO_SHOW = parseInt(INTERVAL_TO_SHOW);

const mapStateToProps = state => ({
  menu: state.menu,
  translation: state.translation,
  userSession: state.user.userSession,
  subscription: state.subscription,
  partner: state.partner
});

const mapDispatchToProps = dispatch => ({
  setSidebar: state => dispatch(setSidebar(state)),
  setVisibility: state => dispatch(setVisibility(state)),
  setUserSubscription: subscription =>
    dispatch(setUserSubscription(subscription)),
  setCommonNotification: param => dispatch(setCommonNotification(param))
});

class Sidebar extends React.Component {
  state = {
    showNotification: false,
    primaryId: ''
  };

  SidebarCloseBtn = () => {
    return (
      <a
        className=""
        style={{ float: 'right' }}
        onClick={() => this.props.setSidebar(false)}
      >
        <img src="/static/img/ic-close.svg" className="search-close" />
      </a>
    );
  };

  async componentDidMount() {
    const { userSession } = this.props;
    const primaryId = this.getUserLoggedInAs();
    this.setState({ primaryId });

    const subscriptionDetails =
      userSession && !userSession.isVisitor
        ? await getSubscriptionDetails()
        : [];
    this.props.setUserSubscription(subscriptionDetails);
  }

  logout = () => {
    window.location.href = '/logout';
  };

  goToLogin = () => {
    Router.pushRoute('/ev/signin');
    this.props.setSidebar(false);
  };

  goToSubscription = () => {
    const { userSession } = this.props;
    const listSubscription = get(userSession, 'subscription', []);

    // TODO: Do we need to check the subscription based of TVOD or not.
    if (listSubscription.length || userSession.isEvWebviewSignin) {
      Router.pushRoute('/subscription');
    } else {
      this.props.setVisibility(false);
      Router.pushRoute('/plans');
    }

    this.props.setSidebar(false);
  };

  onCopyDone = () => {
    const { translation } = this.props;
    this.props.setCommonNotification({
      show: true,
      message: translation.copy_to_clipboard
    });
  };

  getUserLoggedInAs() {
    const { userSession, partner } = this.props;
    const primaryIdType = get(partner, 'actions.user.primaryId.type', false);
    const primaryId = get(userSession, `user.${primaryIdType}`, '');

    if (primaryId !== '') {
      return primaryId;
    }

    const phoneNumber = get(userSession, 'user.phoneNumber', null);
    const email = get(userSession, 'user.email', null);
    const cpCustomerId = get(userSession, 'user.cpCustomerId', null);
    return phoneNumber || email || cpCustomerId;
  }

  render() {
    const { menu, translation, userSession } = this.props;
    const { primaryId } = this.state;
    const { sidebar } = menu;
    const { SidebarCloseBtn } = this;

    return (
      <React.Fragment>
        {sidebar && <div className="sidebar-overlay" />}
        <aside
          id="sidebar-menu"
          className={sidebar ? 'menu slide-in' : 'menu slide-out'}
        >
          <ul className="menu-list">
            <li style={{ paddingBottom: '3rem' }}>
              <SidebarCloseBtn />
            </li>

            {userSession && userSession.isVisitor && (
              <li>
                <a onClick={() => this.goToLogin()}>
                  {translation.sidebarMenu_login}
                </a>
              </li>
            )}

            {userSession && !userSession.isVisitor && (
              <li>
                <a onClick={() => this.goToSubscription()}>
                  {translation.subscription_sidebarMenu}
                </a>
              </li>
            )}

            {/* we should check partner config as well if logout is allowed or not */}
            {userSession &&
              userSession.isEvWebviewSignin &&
              !userSession.isVisitor && (
                <li>
                  <a onClick={() => this.logout()}>
                    {translation.sidebarMenu_logout}
                  </a>
                </li>
              )}
          </ul>

          {userSession && !userSession.isVisitor && (
            <div className="has-text-left sidebar-footer">
              <div className="sidebar-login-as">
                <p className="sidebar-login-as-label">
                  {translation.logged_in_as}
                </p>
                <p className="sidebar-login-as-primaryid">{primaryId}</p>
              </div>
              <div className="sidebar-copy">
                <CopyToClipboard
                  text={primaryId}
                  onCopy={() => this.onCopyDone()}
                >
                  <img src="/static/img/copy.svg" />
                </CopyToClipboard>
              </div>
            </div>
          )}
        </aside>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Sidebar);
