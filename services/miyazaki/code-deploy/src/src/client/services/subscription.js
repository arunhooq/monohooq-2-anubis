import JsonHelper from '../../common/JsonApiHelper';

export const getDetails = async () => {
  let path = `/subscription/status`;
  const res = await JsonHelper.get({ path });
  return res.data;
};

export const createPaymentUrl = async ({ titleId, sku }) => {
  const path = '/payment/createUrl';
  const payload = { titleId, sku };
  const res = await JsonHelper.post({
    path,
    payload,
    withCredentials: true
  });
  return res.data;
};

const convertSKUsToHumanFriendly = plan => {
  return {
    benefits: plan.benefits,
    name: plan.name,
    sku: plan.serviceID,
    price: plan.price,
    duration: plan.duration,
    url: plan.url
  };
};

export const getPlanConfigs = async ({ titleId }) => {
  const path = `/config/plans`;
  const payload = { titleId };
  const res = await JsonHelper.post({ path, payload });
  res.data.plans = res.data.sku.map(item => convertSKUsToHumanFriendly(item));
  delete res.data.sku;

  return res.data;
};

export const cancelSubscription = async (sku = '') => {
  const opts = {
    path: `/subscription/deactivate`,
    payload: {
      sku
    }
  };
  return JsonHelper.post(opts);
};
