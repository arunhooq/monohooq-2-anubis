const { DurationUnit } = require('../../common/General');

export const changeFormatDate = validityTill => {
  if (!validityTill) {
    return '';
  }

  const date = new Date(validityTill);
  const roundNumber = 10;
  let dd = date.getDate();
  let mm = date.getMonth() + 1; //January is 0!

  let yyyy = date.getFullYear();
  if (dd < roundNumber) {
    dd = '0' + dd;
  }
  if (mm < roundNumber) {
    mm = '0' + mm;
  }
  return `${dd}/${mm}/${yyyy}`;
};

export const getPlanTitle = (subscription, translation) => {
  const daysInMonth = 30;
  const { isRenewal, isFreeTrial, duration, durationUnit } = subscription;

  const numberToDisplay =
    durationUnit === DurationUnit.monthly ? duration / daysInMonth : duration;
  const displayPrefix = isFreeTrial
    ? translation.subscription_trial
    : translation.subscription_plan;
  const displaySuffix =
    durationUnit === DurationUnit.monthly
      ? translation.subscription_month
      : translation.subscription_day;

  if (!numberToDisplay) {
    return '-';
  }

  if (isRenewal && duration === daysInMonth) {
    return translation.subscription_monthly;
  } else {
    return `${displayPrefix} ${numberToDisplay} ${displaySuffix}`;
  }
};
