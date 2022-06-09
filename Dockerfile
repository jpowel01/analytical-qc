# Build Stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# Deploy Stage
FROM tomcat:9.0.63
COPY --from=build /usr/src/app/target/app.war /usr/local/tomcat/webapps/app.war
CMD ["catalina.sh", "run"]
