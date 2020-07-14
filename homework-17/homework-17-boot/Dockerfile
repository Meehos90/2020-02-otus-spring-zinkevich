FROM openjdk:8-jre-slim

COPY /target/homework*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
