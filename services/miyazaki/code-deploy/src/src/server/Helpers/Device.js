function checkOSAvailability(OSList = [], device) {
  if (Array.isArray(OSList)) {
    const deviceOS = device.os.toLowerCase();
    if (deviceOS === 'webclient') {
      deviceOS = 'android'; // make android as default
    }
    return OSList.includes(deviceOS);
  }

  return false;
}

module.exports = {
  checkOSAvailability
};
