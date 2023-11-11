FROM openjdk:17-alpine
MAINTAINER nrpndr
WORKDIR /app/movie-service
EXPOSE 9012
ARG JAR_FILE=target/movie-service.jar
COPY ${JAR_FILE} movie-service.jar
ENTRYPOINT ["java","-jar","movie-service.jar"]