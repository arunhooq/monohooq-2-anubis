import logger from '../.../../../common/ClientLogger';
import { getChannels } from '../services/channels';
import {
  injectExternalStylesheet,
  injectExternalScriptTag
} from './../util/pages';

export const filterChannels = channels => {
  return channels.data !== undefined
    ? channels.data
        .filter(row => {
          return row.data !== undefined && row.data !== null;
        })
        .map(row => {
          const rowData = row.data
            .filter(channel => {
              return channel.isPremium === false;
            })
            .map(channel => {
              return {
                id: channel.id.toUpperCase(),
                title: channel.title,
                images: channel.images,
                isPremium: channel.isPremium
              };
            });

          return {
            row_id: row.row_id,
            row_name: row.row_name,
            data: rowData
          };
        })
    : [];
};

export const flattenChannels = channels => {
  return channels
    .map(channelGroup => {
      return channelGroup.data;
    })
    .filter(channelGroup => {
      return channelGroup.length != 0;
    })
    .reduce((a, b) => {
      return a.concat(b);
    });
};

export const getFirstChannel = channels => {
  return flattenChannels(channels)[0];
};

export const staticAssets = [
  {
    isStylesheet: true,
    url: 'https://vjs.zencdn.net/7.3.0/video-js.min.css',
    id: 'videojscss'
  }
];

export const setInitialChannels = async (region, channels, setChannels) => {
  if (channels.length > 0) {
    return;
  }

  const channelGroups = await getChannelCollections(region);
  if (channelGroups.length !== 0) {
    setChannels(channelGroups);
  }
};

export const getChannelCollections = async region => {
  try {
    const channels = await getChannels(region);
    const channelGroups = filterChannels(channels);
    return channelGroups;
  } catch (error) {
    logger.error(error);
    return [];
  }
};

export const initiateScript = assets => {
  assets.map(asset => {
    if (asset.isStylesheet) {
      return injectExternalStylesheet(asset);
    }

    return injectExternalScriptTag(asset);
  });
};
