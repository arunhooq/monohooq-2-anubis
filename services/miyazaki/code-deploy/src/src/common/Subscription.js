function filterActiveSVODSubscriptions(subscriptions) {
  const activeStatus = ['Active', 'Final Bill']; // TODO: Move this into constant
  const activeSVODSubscriptions = subscriptions.filter(s => {
    return activeStatus.includes(s.status) && s.VODItemMsg === undefined; // TODO: is VODItemMsg checking still needed?
  });
  return activeSVODSubscriptions;
}

module.exports = {
  filterActiveSVODSubscriptions
};
