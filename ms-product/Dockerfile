FROM openjdk:11-jdk
RUN mkdir /app
WORKDIR /app
COPY target/ms-product-0.0.1.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/ms-product-0.0.1.jar", "--spring.profiles.active=docker"]
