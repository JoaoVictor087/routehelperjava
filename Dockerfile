# Stage 1: Build the Quarkus application
FROM registry.access.redhat.com/ubi9/openjdk-21:1.21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw package -Dnative=false -DskipTests

# Stage 2: Create the runtime image
FROM registry.access.redhat.com/ubi9/openjdk-21:1.21
WORKDIR /deployments
COPY --from=builder /app/target/*-runner.jar app.jar
EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/app.jar"
ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]