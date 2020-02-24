const defaultState = {
  channels: [],
  channelId: '',
  hls: ''
};

export default function channelReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setChannels':
      return { ...state, channels: action.payload };
    case 'setChannelId':
      return { ...state, channelId: action.payload };
    case 'setHls':
      return { ...state, hls: action.payload };
    default:
      return state;
  }
}
