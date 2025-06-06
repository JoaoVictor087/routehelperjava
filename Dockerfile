# Use the Eclipse temurin alpine official image
FROM eclipse-temurin:21-jdk-alpine

# Create and change to the app directory.
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

# Make the Maven wrapper executable
RUN chmod +x ./mvnw

# Build the app.
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Run the quarkus app
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]