const verifyPWT = {
  acr: '["service:PASSENGER"]',
  aud: '08044981144746ec80a870c605fe705b',
  exp: 1541121066,
  iat: 1540861866,
  iss: 'https://idp.grab.com',
  jti: 'GPrwi0qMQ2Cnl5esz4C2kA',
  nbf: 1540861866,
  nonce: '4zMMjv99SuPn11vL',
  pid: '6de31ff0-1502-493d-90da-b49e42774c59',
  sub: 'f44c3fdd-e6d4-4498-b865-7a8b7afda128',
  svc: 'PASSENGER'
};

module.exports = [
  {
    path:
      '/grabid/v1/oauth2/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY3IiOiJbXCJzZXJ2aWNlOlBBU1NFTkdFUlwiXSIsImF1ZCI6IjA4MDQ0OTgxMTQ0NzQ2ZWM4MGE4NzBjNjA1ZmU3MDViIiwiZXhwIjoxNTQxMTIxMDY2LCJpYXQiOjE1NDA4NjE4NjYsImlzcyI6Imh0dHBzOi8vaWRwLmdyYWIuY29tIiwianRpIjoiR1Byd2kwcU1RMkNubDVlc3o0QzJrQSIsIm5iZiI6MTU0MDg2MTg2Niwibm9uY2UiOiI0ek1NanY5OVN1UG4xMXZMIiwicGlkIjoiNmRlMzFmZjAtMTUwMi00OTNkLTkwZGEtYjQ5ZTQyNzc0YzU5Iiwic3ViIjoiZjQ0YzNmZGQtZTZkNC00NDk4LWI4NjUtN2E4YjdhZmRhMTI4Iiwic3ZjIjoiUEFTU0VOR0VSIn0.0V-MLGd9ntM2oyOQrS8nuByRmrQpeGVgQOeGe2Y_EiA/token_info',
    method: 'get',
    body: verifyPWT
  }
];
