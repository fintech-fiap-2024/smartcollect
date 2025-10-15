FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src


ARG JAR_NAME="smartcollect-0.0.1-SNAPSHOT.jar"
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

ENV JAVA_OPTS="-XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=75.0"

EXPOSE 8080

COPY --from=build /app/target/smartcollect-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "/app.jar"]
