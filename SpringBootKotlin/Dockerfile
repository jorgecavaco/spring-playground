FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY ./build/libs /app
WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["java","-jar","server.jar"]