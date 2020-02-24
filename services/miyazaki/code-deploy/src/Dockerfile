# Stage 1 (base)
FROM node:10.18-alpine3.11 as builder

EXPOSE 4352

# SSH private and public key
ARG SSH_PRV_KEY
ARG SSH_PUB_KEY

# Set ssh service
RUN apk add --no-cache openssh  \
    && apk add --no-cache curl \
    && apk add --no-cache git

# Authorize SSH Host
RUN mkdir -p /root/.ssh && \
    chmod 0700 /root/.ssh && \
    ssh-keyscan github.com > /root/.ssh/known_hosts

# Add the keys and set permissions
RUN echo "$SSH_PRV_KEY" > /root/.ssh/id_rsa && \
    echo "$SSH_PUB_KEY" > /root/.ssh/id_rsa.pub && \
    chmod 600 /root/.ssh/id_rsa && \
    chmod 600 /root/.ssh/id_rsa.pub

# Set NODE_ENV
ARG ARG_NODE_ENV
ENV NODE_ENV ${ARG_NODE_ENV}

WORKDIR /usr/local/miyazaki

# Install yarn
ENV YARN_VERSION 1.19.1
RUN curl -fSLO --compressed "https://yarnpkg.com/downloads/$YARN_VERSION/yarn-v$YARN_VERSION.tar.gz" \
    && tar -xzf yarn-v$YARN_VERSION.tar.gz -C /opt/ \
    && ln -snf /opt/yarn-v$YARN_VERSION/bin/yarn /usr/local/bin/yarn \
    && ln -snf /opt/yarn-v$YARN_VERSION/bin/yarnpkg /usr/local/bin/yarnpkg \
    && rm yarn-v$YARN_VERSION.tar.gz

# Install Dependency
COPY package.json /usr/local/miyazaki
COPY yarn.lock /usr/local/miyazaki
RUN yarn install

# Build Application
COPY . /usr/local/miyazaki
RUN yarn build

# Stage 2 (production)
FROM node:10.18-alpine3.11 as prod

# Set NODE_ENV
ARG ARG_NODE_ENV
ENV NODE_ENV ${ARG_NODE_ENV}

WORKDIR /usr/local/miyazaki

# Copy built application
COPY --from=builder /usr/local/miyazaki /usr/local/miyazaki

RUN ls -al src/client/.next

CMD ["node","./src/index.js"]
