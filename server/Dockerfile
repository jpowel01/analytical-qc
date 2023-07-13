# Build Stage
FROM maven:3.9.1-amazoncorretto-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM amazoncorretto:17-alpine
COPY --from=build /home/app/target/analytical-qc-0.0.1-SNAPSHOT.jar /usr/local/lib/analytical-qc-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/usr/local/lib/analytical-qc-0.0.1-SNAPSHOT.jar"]
