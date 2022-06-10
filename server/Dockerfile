FROM maven:3.8.5-openjdk-18-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
ENTRYPOINT ["java", "-jar", "/usr/src/app/target/analytical-qc-0.0.1-SNAPSHOT.jar"]