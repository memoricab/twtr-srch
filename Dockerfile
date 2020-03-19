FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar","-Dspring.profiles.active=${ENV}","-Dspring-boot.run.arguments=--server.port=${APP_PORT},--spring.datasource.url=${DATA_SOURCE_URL},--spring.datasource.username=${DATA_SOURCE_USERNAME},--spring.datasource.password=${DATA_SOURCE_PASSWORD}"]