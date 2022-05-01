FROM openjdk:11-jdk
RUN mkdir /app
WORKDIR /app
COPY target/ms-order-0.0.1.jar /app
ENTRYPOINT ["java", "-jar", "/app/ms-order-0.0.1.jar"]
EXPOSE 8085
