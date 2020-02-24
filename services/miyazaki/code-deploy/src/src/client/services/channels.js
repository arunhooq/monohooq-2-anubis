import getConfig from 'next/config';
import JsonHelper from '../../common/JsonApiHelper';

const { publicRuntimeConfig } = getConfig();

const CHANNEL_URL = publicRuntimeConfig.CHANNEL_BASE_URL;

export const getManifest = async ({ channelId }) => {
  let path = `/tv/play/${channelId}`;

  return JsonHelper.get({
    path,
    withCredentials: true
  });
};

export const getChannels = async (region, page = 1, perPage = 25) => {
  const opts = {
    path: `${CHANNEL_URL}/discover/tv/channels`,
    payload: {
      region,
      page,
      perPage
    }
  };

  return JsonHelper.get(opts);
};
