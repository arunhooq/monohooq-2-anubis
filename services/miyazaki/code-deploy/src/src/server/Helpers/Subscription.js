const { get } = require('lodash');
const { Subscription, DurationUnit } = require('../../common/General');
const { GENERIC_ERROR } = require('../../common/ErrorCodes');
const { EveErrors } = require('../../common/ErrorCodes');

function checkStatus(ctx, activeSVODSubscriptions, eligibility) {
  if (activeSVODSubscriptions.length > 0) {
    return Subscription.ACTIVE;
  }

  if (!isEligibleForFreeTrial(ctx, eligibility)) {
    return Subscription.LAPSED;
  }

  return Subscription.NEW_USER;
}

function isEligibleForFreeTrial(ctx, eligibility) {
  if (eligibility.data && eligibility.data.message === 'Free Trial Available') {
    return true;
  }

  if (ctx && eligibility.name === 'ApiError') {
    const code = get(
      EveErrors,
      `${eligibility.errorCode}.code`,
      GENERIC_ERROR.errorCode
    );
    const message = get(
      EveErrors,
      `${eligibility.errorCode}.message`,
      GENERIC_ERROR.message
    );
    ctx.state.logger.info(`${ctx.state.logger.key}-eligible-for-free-trial`, {
      partnerId: ctx.state.partnerId,
      response: {
        sessionId: ctx.state.sessionId,
        code,
        message
      }
    });
  }

  return false;
}

function addSubscriptionProperties(ctx, subscriptions) {
  const completeSubscriptions = subscriptions.map(subscription => {
    const {
      defaultSku
    } = ctx.state.partnerConfiguration.CustomConfig.constants;

    const dayRemaining = getRemainingDay(subscription.validityTill);
    const isFreeTrial = isSKUFreeTrial(
      subscription.serviceID,
      defaultSku.serviceID
    );
    const isRenewal = subscription.isRenewal ? subscription.isRenewal : false;
    const duration = parseInt(subscription.duration, 10);
    const durationUnit =
      duration >= 30 ? DurationUnit.monthly : DurationUnit.daily;

    return {
      ...subscription,
      dayRemaining,
      isFreeTrial,
      isRenewal,
      durationUnit,
      duration
    };
  });
  return completeSubscriptions;
}

function getRemainingDay(validityTill) {
  const validUntil = new Date(validityTill);
  const diff = validUntil - new Date();
  const millisecondDivider = 8.64e7;
  const days = (diff / millisecondDivider) | 0;
  return days;
}

function isSKUFreeTrial(sku, defaultSku) {
  return sku === defaultSku;
}

module.exports = {
  checkStatus,
  isEligibleForFreeTrial,
  addSubscriptionProperties
};
