FROM openjdk:8-jre-slim

COPY /target/diagnostic*.jar /diagnostic.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/diagnostic.jar"]