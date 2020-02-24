import get from 'lodash.get';
import { changeFormatDate } from './subscription';

export const getRewardsDetail = ({
  partnerConfig,
  subscription,
  translation,
  activatedWith
}) => {
  let title = '';
  let subtitle = '';

  const validSKU = partnerConfig.constants.rewardsSKU.filter(
    sku => sku.serviceID === activatedWith
  );

  if (subscription.length && validSKU.length) {
    const plan = get(validSKU, '[0].plan', '');
    title = get(translation, `rewards_${plan}_title`, '');
    subtitle = get(translation, `rewards_${plan}_subtitle`, '');

    const totalSubscriptions = subscription.length;
    const sku = subscription[totalSubscriptions - 1];
    const validityDate = changeFormatDate(sku.validityTill);
    subtitle = subtitle ? subtitle.replace('{0}', validityDate) : '';
  }

  return { title, subtitle };
};
