import JsonHelper from '../../common/JsonApiHelper';

export const putPlay = async ({ titleId, length, position }) => {
  const opts = {
    path: `/play/${titleId}`,
    withCredentials: true,
    payload: {
      length,
      position
    }
  };

  return JsonHelper.put(opts);
};
