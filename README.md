# Market-microservices

## How to run?

### Build all modules:

`market$ mvn -DskipTests clean package`

### Build infrastructure modules in docker:

`market$ ./build-docker-image.sh`

### Start all microservices:

`market$ docker-compose up -d`

### When all microservices are started, go to the browser and use the path:
  `http://localhost/index.html`

## Project Goals:
- Using the microservice architecture
- Сreate microservices: product, order, authentification
- Using Spring Cloud
- Storing JWT tokens and Carts in Redis
- Make simple frontend on AngularJS

## Features
- Java
- HTML + JS + AngularJS
- Spring Web, Rest, Data
- Spring Security + JWT
- Redis
- Spring Cloud (Discovery server Netflix Eureka + OpenFeign + Gateway)
- Flyway + H2
- Lombok
- Docker

## Screenshot of the catalog page
![image](https://user-images.githubusercontent.com/51756264/166443339-82b87680-293c-4cab-ab24-6f6bdb6ae27d.png)


## Screenshot of the architecrure
![image](https://user-images.githubusercontent.com/51756264/120798356-e9271500-c545-11eb-98f2-7f184b1759c9.png)
