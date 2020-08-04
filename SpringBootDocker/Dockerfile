FROM openjdk:11.0.7-jre-slim

LABEL maintainer="John Doe"
EXPOSE 8081

RUN adduser --system --no-create-home spring

ENV PROFILE "default"
ENV OPS="-Dspring.profiles.active=\${PROFILE}"

WORKDIR /opt

USER spring

COPY target/demo-0.0.1-SNAPSHOT.jar /opt/app.jar

ENTRYPOINT ["java","-jar", "/opt/app.jar","${OPS}"]