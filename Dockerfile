####
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the container image run:
#
# ./mvnw package
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.jvm -t quarkus/routehelper-jvm .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/routehelper-jvm
#
###
FROM registry.access.redhat.com/ubi9/openjdk-21:1.21

ENV LANGUAGE='en_US:en'

# Criar o diretório de deployments
WORKDIR /deployments

# Copiar apenas o JAR gerado pelo Quarkus
COPY --chown=185 target/*-runner.jar app.jar

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/app.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]