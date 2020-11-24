FROM openjdk:11-jdk

ENV POSTGRESQL_HOST postgresql

COPY target/springboot-api-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]