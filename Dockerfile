FROM maven:3.8.5-openjdk-18-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
COPY /usr/src/app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]