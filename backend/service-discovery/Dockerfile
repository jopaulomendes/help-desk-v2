FROM openjdk:21-jdk
ARG JAR_FILE=./build/libs/*.jar
EXPOSE 8761
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker container run -d -p 8761:8761 --name helpdesk-service-discovery -e CONFIG_SERVER_URI=http://localhost:8888 jopaulomendes/helpdesk-service-discovery:latest

# docker container rm $(docker ps -a -q) -> remove todos os containers

# docker ps -a -> lista os containes rodando