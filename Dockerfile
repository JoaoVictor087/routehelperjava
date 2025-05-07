# Stage 1: Build the Quarkus application
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src
RUN chmod +x ./mvnw
RUN ./mvnw package  -DskipTests

# Stage 2: Run the Quarkus application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=0 /app/target/routehelper-1.0-SNAPSHOT-runner.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]