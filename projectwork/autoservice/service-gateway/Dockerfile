FROM openjdk:8-jre-slim

COPY /target/service-gateway*.jar /service-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/service-gateway.jar"]