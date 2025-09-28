FROM jenkins/agent:alpine-jdk21

USER root

RUN apk add --no-cache maven \
    && apk add --no-cache docker \
    && rm -rf /var/cache/apk/*

USER jenkins
