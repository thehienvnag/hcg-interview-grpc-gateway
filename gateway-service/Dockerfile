FROM maven:3.8.4-openjdk-11-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim-buster

WORKDIR /app
COPY --from=builder /app/target/*.jar app-runner.jar

ARG GATEWAY_DEBUG_PORT
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$GATEWAY_DEBUG_PORT

ENTRYPOINT ["java", "-jar", "app-runner.jar"]