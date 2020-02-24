ARG FILEBEAT_VERSION=7.5.2
FROM docker.elastic.co/beats/filebeat:${FILEBEAT_VERSION}
USER root
RUN yum update -y && \
    yum clean all && \
    mkdir /var/log/pm2 && \
    mkdir /var/log/nginx
COPY filebeat.yml /usr/share/filebeat/filebeat.yml
RUN chown root:filebeat /usr/share/filebeat/filebeat.yml
USER filebeat
