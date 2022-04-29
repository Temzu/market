FROM openjdk:11-jdk
RUN mkdir /app
WORKDIR /app
COPY target/front-server-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/front-server-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
