const { get } = require('lodash');

function calculateProgress(movie) {
  const attr = get(movie, 'attributes.lastWatched', movie.attributes);
  const { position, duration } = attr;
  const percentage = (position / duration) * 100;
  return Math.round(percentage);
}

function calculateTimeLeft(movie) {
  const attr = get(movie, 'attributes.lastWatched', movie.attributes);
  const { position, duration } = attr;
  const dividedSecondPerMinute = 1 / 60; // 1second divided sum of seconds per minute
  const left = duration - position;
  return Math.round(left * dividedSecondPerMinute);
}

module.exports = {
  calculateProgress,
  calculateTimeLeft
};
