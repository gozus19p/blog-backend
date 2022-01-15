FROM alpine:latest

# Installing Java 11
RUN apt-get update && apt-get install -y openjdk-11-jdk

# Preparing sources
CMD mkdir /app/sources
COPY . /app/sources
WORKDIR /app/sources
VOLUME /app

# Building from source
CMD sh ./gradlew clean bootJar
CMD mv ./build/libs/* /app/app.jar

# Removing sources
CMD rm -rf /app/sources

# Starting application
ENTRYPOINT java -jar /app/app.jar