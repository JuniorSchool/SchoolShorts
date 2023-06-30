FROM eclipse-temurin:17
WORKDIR /app
COPY target/SchoolShorts-0.0.1-SNAPSHOT.jar /app/schoolshorts.jar
ENTRYPOINT ["java", "-jar", "schoolshorts.jar"]
