### STAGE 1: Build ###
FROM node:13.3.0-alpine as builder
EXPOSE 3000
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY package.json /usr/src/app/package.json
RUN npm install --verbose
RUN npm install react-scripts -g --silent
COPY . /usr/src/app
RUN npm run build
CMD ["npm", "start"]

