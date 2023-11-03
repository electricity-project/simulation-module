#
# Build stage
#
# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jdk-jammy AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD .. $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/simulation-mqtt-consumer/pom.xml clean package -DskipTests spring-boot:repackage

#
# Package stage
#
FROM eclipse-temurin:21-jre-jammy
ARG JAR_FILE=/usr/app/simulation-mqtt-consumer/target/*.jar
COPY --from=build $JAR_FILE /app/simulation-mqtt-consumer.jar
ENTRYPOINT java -jar /app/simulation-mqtt-consumer.jar
