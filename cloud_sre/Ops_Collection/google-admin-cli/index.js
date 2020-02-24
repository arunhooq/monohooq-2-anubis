const fs = require('fs');
const readline = require('readline');
const { google } = require('googleapis');

// If modifying these scopes, delete token.json.
const SCOPES = ['https://www.googleapis.com/auth/admin.directory.user'];
// The file token.json stores the user's access and refresh tokens, and is
// created automatically when the authorization flow completes for the first
// time.
const TOKEN_PATH = 'token.json';

// Local client secrets from a local file
fs.readFile('credentials.json', (err, content) => {
  if (err) return console.error('Error loading client secret file', err);

  // Authorize a client with the loaded credentials, then call the
  // Directory API
  // authorize(JSON.parse(content), listUsers);
  authorize(JSON.parse(content), batchPatchUserAWSRole);
  // authorize(JSON.parse(content), getUser);
});

/**
 * Create an OAuth2 client with the given credentials, and then execute the
 * given callback function.
 *
 * @param {Object} credentials The authorization client credentials.
 * @param {function} callback The callback to call with the authorized client.
 */
function authorize (credentials, callback) {
  const { client_secret, client_id, redirect_uris } = credentials.installed;
  const oauth2Client = new google.auth.OAuth2(
    client_id, client_secret, redirect_uris[0]);

  // Check if we have previously stored a token.
  fs.readFile(TOKEN_PATH, (err, token) => {
    if (err) return getNewToken(oauth2Client, callback);
    oauth2Client.credentials = JSON.parse(token);
    callback(oauth2Client);
  });
}

/**
 * Get and store new token after prompting for user authorization, and then
 * execute the given callback with the authorized OAuth2 client.
 *
 * @param {google.auth.OAuth2} oauth2Client The OAuth2 client to get token for.
 * @param {getEventsCallback} callback The callback to call with the authorized
 *     client.
 */
function getNewToken (oauth2Client, callback) {
  const authUrl = oauth2Client.generateAuthUrl({
    access_type: 'offline',
    scope: SCOPES
  });
  console.log('Authorize this app by visiting this url:', authUrl);
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });
  rl.question('Enter the code from that page here: ', (code) => {
    rl.close();
    oauth2Client.getToken(code, (err, token) => {
      if (err) return console.error('Error retrieving access token', err);
      oauth2Client.credentials = token;
      storeToken(token);
      callback(oauth2Client);
    });
  });
}

/**
 * Store token to disk be used in later program executions.
 *
 * @param {Object} token The token to store to disk.
 */
function storeToken (token) {
  fs.writeFile(TOKEN_PATH, JSON.stringify(token), (err) => {
    if (err) return console.warn(`Token not stored to ${TOKEN_PATH}`, err);
    console.log(`Token stored to ${TOKEN_PATH}`);
  });
}

/**
 * Lists the first 10 users in the domain.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client.
 */
function listUsers (auth) {
  const service = google.admin({ version: 'directory_v1', auth });
  service.users.list({
    customer: 'my_customer',
    maxResults: 10,
    orderBy: 'email'
  }, (err, res) => {
    if (err) return console.error('The API returned an error:', err.message);

    const users = res.data.users;
    if (users.length) {
      console.log('Users:');
      users.forEach((user) => {
        console.log(`${user.primaryEmail} (${user.name.fullName})`);
      });
    } else {
      console.log('No users found.');
    }
  });
}

/**
 * GET user from G Suite API.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client
 */
function getUser (auth) {
  const service = google.admin({ version: 'directory_v1', auth });
  service.users.get({
    userKey: 'ion@hooq.tv',
    projection: 'full'
  }, (err, res) => {
    if (err) return console.error('The API returned an error:', err.message);

    console.log(res.data.customSchemas.Amazon_Web_Services);
  });
}

/**
 * Patch user to have aws role access.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client
 * @param {string} email Email to be udpated
 */
function patchUserAdminAWSRole (auth, email) {
  const service = google.admin({ version: 'directory_v1', auth });
  service.users.patch({
    userKey: email,
    requestBody: {
      customSchemas: {
        Amazon_Web_Services: {
          IAM_role: [
            {
              type: 'work',
              value: 'arn:aws:iam::782273850218:role/hooq-play-full-access,arn:aws:iam::782273850218:saml-provider/gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::064976301468:role/hooq-dev-full-access,arn:aws:iam::064976301468:saml-provider/gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::906191743243:role/hooq-stag-full-access,arn:aws:iam::906191743243:saml-provider/gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::483024684374:role/hooq-prod-full-access,arn:aws:iam::483024684374:saml-provider/gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::669435643442:role/hooq-billing-full-access,arn:aws:iam::669435643442:saml-provider/gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::436712201231:role/hooq-sre-full-access,arn:aws:iam::436712201231:saml-provider/Gsuite'
            },
            {
              type: 'work',
              value: 'arn:aws:iam::242897539324:role/hooq-data-full-access,arn:aws:iam::242897539324:saml-provider/GSuite'
            }
          ]
        }
      }
    }
  }, (err, res) => {
    if (err) return console.error('The API returned an error', err.message);
  });
}

/**
 * Patch user to have aws role access.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client
 * @param {string} email Email to be udpated
 */
function patchUserDevAWSRole (auth, email) {
  const service = google.admin({ version: 'directory_v1', auth });
  service.users.patch({
    userKey: email,
    requestBody: {
      customSchemas: {
        Amazon_Web_Services: {
          IAM_role: [
            {
              type: 'work',
              value: 'arn:aws:iam::782273850218:role/hooq-play-limited-read-write-access,arn:aws:iam::782273850218:saml-provider/gsuite'
            }
          ],
          SessionDuration: '28800'
        }
      }
    }
  }, (err, res) => {
    if (err) return console.error('The API returned an error', err.message + ' ' + email);
  });
}

/**
 * Patch user to have aws role access.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client
 */
function batchPatchUserAWSRole (auth) {
  const emails = ['huijie@hooq.tv', 'alex@hooq.tv', 'indra.santosa@hooq.tv', 'yalian@hooq.tv'];
  for (const email of emails) {
    console.log(email);
    patchUserDevAWSRole(auth, email);
  }
}