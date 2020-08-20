FROM openjdk:8-jre-slim

COPY /target/storage*.jar /storage.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/storage.jar"]