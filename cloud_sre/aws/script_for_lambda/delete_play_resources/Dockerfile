FROM python:3-slim

ENV AWS_DEFAULT_REGION=def_value AWS_ACCESS_KEY_ID=def_value AWS_SECRET_ACCESS_KEY=def_value

COPY . .
RUN pip3 install awscli --upgrade --user && \
         apt-get update && \
         apt-get install curl -y && \
         curl -L -O https://github.com/cloudetc/awsweeper/releases/download/v0.4.0/awsweeper_0.4.0_linux_amd64.tar.gz && \
         tar -xzf awsweeper_0.4.0_linux_amd64.tar.gz && chmod +X awsweeper && \
         rm awsweeper_0.4.0_linux_amd64.tar.gz

ENTRYPOINT ["./awsweeper", "--force", "all.yml"]
