FROM openjdk:8-jre-slim

RUN ln -s /dev/stdout /var/log/httpd/access_log && \
    ln -s /dev/stderr /var/log/httpd/error_log

COPY /target/homework-17-1.0-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
