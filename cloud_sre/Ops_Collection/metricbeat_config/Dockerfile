ARG METRICBEAT_VERSION=7.5.2
FROM docker.elastic.co/beats/metricbeat:${METRICBEAT_VERSION}
COPY metricbeat.yml /usr/share/metricbeat/metricbeat.yml
USER root
RUN chown root:metricbeat /usr/share/metricbeat/metricbeat.yml
USER metricbeat
