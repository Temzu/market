FROM openjdk:11-jdk
RUN mkdir /app
WORKDIR /app
COPY target/eureka-server-0.0.1.jar /app
ENTRYPOINT ["java", "-jar", "/app/eureka-server-0.0.1.jar"]
EXPOSE 8761