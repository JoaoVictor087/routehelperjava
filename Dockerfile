# Stage 1: Build the Quarkus application
FROM registry.access.redhat.com/ubi9/openjdk-21:1.21 AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src
RUN chmod +x ./mvnw
RUN ./mvnw package -Dnative=false -DskipTests