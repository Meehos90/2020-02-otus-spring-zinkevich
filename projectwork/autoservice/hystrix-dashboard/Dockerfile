FROM openjdk:8-jre-slim

COPY /target/hystrix-dashboard*.jar /hystrix-dashboard.jar
EXPOSE 7979
ENTRYPOINT ["java", "-jar", "/hystrix-dashboard.jar"]