FROM openjdk:8-jre-slim

COPY /target/storage*.jar /app/storage.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/storage.jar"]