FROM openjdk:11-jdk
RUN mkdir /app
WORKDIR /app
ENV REDIS_HOST=redis-service
ENV REDIS_PORT=6379
COPY target/ms-order-0.0.1.jar /app
ENTRYPOINT ["java", "-jar", "/app/ms-order-0.0.1.jar"]
EXPOSE 8085
