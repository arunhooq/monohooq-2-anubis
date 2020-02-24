import { MediaModule } from '@grabjs/superapp-sdk';
import JsonApiHelper from '../../common/JsonApiHelper';
import logger from '../../common/ClientLogger';
import { heartbeat } from '../helpers/bridge/heartbeat';
import ClientError from '../helpers/error';
import { UNKNOWN_DEVICE } from '../../common/ErrorCodes';
import { PORTRAIT_PRIMARY } from '../constants/common.js';

export default class Consent {
  static getScreenWidth(screen) {
    return screen.orientation === PORTRAIT_PRIMARY
      ? screen.height
      : screen.width;
  }

  static getScreenHeight(screen) {
    return screen.orientation === PORTRAIT_PRIMARY
      ? screen.width
      : screen.height;
  }

  static async getPlay({ movieId, screen }) {
    const deviceWidth = this.getScreenWidth(screen);
    const deviceHeight = this.getScreenHeight(screen);

    return await JsonApiHelper.get({
      withCredentials: true,
      path: `/play/${movieId}`,
      payload: {
        deviceWidth,
        deviceHeight
      }
    });
  }

  static nativePlay(response, isHeartbeatEnabled = false) {
    if (window.MediaKit || window.webkit) {
      try {
        const mediaModule = new MediaModule();
        mediaModule.playDRMContent(response.data).subscribe({
          next: ({ result, error, status_code }) => {
            if (!!result) {
              const { type, titleId, length, position } = result;
              if (type === 'PROGRESS_PLAYBACK' && isHeartbeatEnabled) {
                heartbeat({ titleId, length, position });
              }
            }

            if (!!error) {
              throw new Error(error);
            }
          },
          complete: () => {
            // TODO:Completion logic here when the stream stops
          }
        });
      } catch (err) {
        logger.error(err);
        this.nativeJsBridgePlay(response);
      }
    } else {
      throw new ClientError(UNKNOWN_DEVICE.message, UNKNOWN_DEVICE.errorCode);
    }
  }

  nativeJsBridgePlay(response) {
    if (window.MediaKit) {
      // Android
      window.MediaKit.playDRMContent(JSON.stringify(response));
    }

    if (window.webkit) {
      // iOS
      window.webkit.messageHandlers.MediaKit.postMessage({
        method: 'playDRMContent',
        parameters: response.data
      });
    }
  }
}
