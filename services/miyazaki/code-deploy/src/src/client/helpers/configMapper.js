import {
  setConstants,
  setPartnerDetail,
  setRestriction,
  setUIConfig,
  setActions
} from '../actions/partnerAction';

export const partnerDataMapper = partnerConfig => {
  const partnerDetail = {
    channelPartnerID: partnerConfig.ChannelPartnerID,
    partnerId: partnerConfig.PartnerId,
    isActive: partnerConfig.IsActive,
    id: partnerConfig.ID
  };

  const { ui, restriction, constants, actions } = partnerConfig.CustomConfig;
  return { partnerDetail, uiConfig: ui, restriction, constants, actions };
};

export const configMapper = (partnerConfig, store) => {
  const {
    partnerDetail,
    constants,
    restriction,
    uiConfig,
    actions
  } = partnerConfig;
  store.dispatch(setPartnerDetail(partnerDetail));
  store.dispatch(setConstants(constants));
  store.dispatch(setRestriction(restriction));
  store.dispatch(setUIConfig(uiConfig));
  store.dispatch(setActions(actions));
};
