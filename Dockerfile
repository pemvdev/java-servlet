FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ src/
RUN mvn package -DskipTests

FROM tomcat:9-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/java-servlet-1.0.war /usr/local/tomcat/webapps/java-servlet.war

EXPOSE 8080