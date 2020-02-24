# Miyazaki Webview

Miyazaki is a webview as the part of partner integration.

## Setup

- DynamoDB local installation https://github.com/HOOQTV/stairway#setup-dynamodb
- Kong local installation https://github.com/HOOQTV/stairway#setup-kong
- Stairway local setup https://github.com/HOOQTV/stairway#setup-api
- Kashmir local setup https://github.com/HOOQTV/kashmir

## Installation

Use the package manager yarn to install miyazaki.

```bash
> yarn install
> cp deploy/development/dotenv .env
```

## Usage

```
> yarn dev
```

## Testing

### Server

Setup:

1. Ensure redis is running

To test the server run the following command:

```sh
$ yarn test:server
```

### Client 

To test the client run the following command:

```sh
$ yarn test:client
```

## Partner Setup

To use this webview on local, you have to add domain `{partnerId}.hooq.local` in your `/etc/hosts` like below. This is mandatory since we specified our partner based on the subdomain.

```
127.0.0.1     grab.hooq.local       # Indonesia Market GRAB
127.0.0.1     sg-grab.hooq.local    # Singapore Market GRAB
```

### Current Partner ID

- grab (Indonesia market)
- sg-grab (Singapore market)

### Future Partners

- phonepe (India)
- etc
