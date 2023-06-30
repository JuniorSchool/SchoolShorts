FROM maven:3.8.7-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
#FROM openjdk:22-slim
FROM eclipse-temurin:20-jre
ENV CONTEXT_PATH=/schoolshorts
COPY --from=build /home/app/target/SchoolShorts-0.0.1-SNAPSHOT.jar /usr/local/lib/schoolshorts.jar
EXPOSE 8080
#ENTRYPOINT java -jar /usr/local/lib/schoolshorts.jar
ENTRYPOINT java -jar /usr/local/lib/schoolshorts.jar --server.servlet.context-path=${CONTEXT_PATH}
