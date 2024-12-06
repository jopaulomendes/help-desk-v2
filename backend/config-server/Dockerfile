FROM openjdk:21-jdk
ARG JAR_FILE=./build/libs/*.jar
EXPOSE 8888
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker container run -d -p 8888:8888 --name helpdesk-config-server -e CONFIG_SERVER_URI=https://github.com/jopaulomendes/help-desk-v2-config -e CONFIG_SERVER_USERNAME=jopaulomendes -e CONFIG_SERVER_PASSWORD=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 jopaulomendes/helpdesk-config-server:latest

# docker container rm $(docker ps -a -q) -> remove todos os containers