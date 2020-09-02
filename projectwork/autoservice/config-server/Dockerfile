FROM openjdk:8-jre-slim

COPY /target/config-server*.jar /config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/config-server.jar"]