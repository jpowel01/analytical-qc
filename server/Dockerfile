FROM maven:3.8.5-openjdk-18-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ccte-postgres-res.dmap-prod.aws.epa.gov:5432/res_gsincl01_qsar
ENV SPRING_DATASOURCE_USERNAME=app_qsar
ENV SPRING_DATASOURCE_PASSWORD=P#BM8&TEEdg@Aj#r
ENV SPRING_DEFAULT_SCHEMA=sbox_analytical_qc
RUN mvn -f /usr/src/app/pom.xml clean package
ENTRYPOINT ["java", "-jar", "/usr/src/app/target/analytical-qc-0.0.1-SNAPSHOT.jar"]