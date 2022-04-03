#
# Set a variable that can be used in all stages.
#
ARG BUILD_HOME=/gradle-docker-example

#
# Gradle image for the build stage.
#
FROM gradle:jdk11 as build-image

#
# Set the working directory.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

#
# Copy the Gradle config and source code into the build container.
#
COPY --chown=gradle:gradle build.gradle settings.gradle libs.versions.toml $APP_HOME/
COPY --chown=gradle:gradle src $APP_HOME/src

#
# Build the application.
#
RUN gradle --no-daemon build

#
# Java image for the application to run in.
#
FROM openjdk:12-alpine

#
# Copy the jar file in and name it app.jar.
#
ARG BUILD_HOME
ARG MRVN_TOKEN
ARG APEX_API_KEY
ARG TEST_SERVER

ENV MRVN_TOKEN=$MRVN_TOKEN
ENV APEX_API_KEY=$APEX_API_KEY
ENV TEST_SERVER=$TEST_SERVER
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/mrvn*-all.jar app.jar

#
# The command to run when the container starts.
#
ENTRYPOINT ["java -jar app.jar"]
