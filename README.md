# Java Rest API 2 Server

Java Rest API 2 Server/Web-UI is a project based Java Rest API CURD functionalities to Create, Delete, Get server objects using json-encoded message body(postman) or using Web UI.
# Tech Stack

* Java 
* Spring Boot
* MongoDB
* Maven
* Git
* Docker

# Dependencies
Using Spring Initializer from [Spring Boot](https://start.spring.io/) to add the below mentioned dependencies.


![depedencies](https://user-images.githubusercontent.com/64476159/163684974-4ad87255-93e3-4a95-b9da-ad67539a5b8c.png)

Given Java Rest API 2 Server application tree structure outlined below.
# Spring Boot Application and Maven files
```
└───server2api
    ├───src
    │   ├───main
    │   │   ├───java
    │   │   │   └───com
    │   │   │       └───task1
    │   │   │           └───server2api
    │   │   │               ├───controller
    │   │   │               ├───entity
    │   │   │               ├───repository
    │   │   │               └───service
    │   │   │                   └───impl
    │   │   └───resources
    │   │       ├───static
    │   │       └───templates
    │   └───test
    │       └───java
    │           └───com
    │               └───task1
    │                   └───server2api
   
```

## Running the Application

```bash
## Run MongoDB service
mongod -bind_ip_all

## build application 
mvn package 

## Run application using Java -jar command
java -jar /target/< filename >.jar

## Run application using Maven
maven spring-boot:run

```
## Docker Build
* Creating a Dockerfile for task4 kubernetes Deployment.
```
FROM openjdk:18-jdk-alpine
RUN mkdir app
COPY <filename>.jar /app/app.jar
EXPOSE 6039
EXPOSE 27017
ENTRYPOINT ["java","-jar","/app/app.jar"] 
```
* Build the image
```
docker build -t app:v01 .
```
* Push it to Docker Repo
```
docker push task/app:v01
```
![docker push from tk1](https://user-images.githubusercontent.com/64476159/163685072-ff3efca4-7679-44e4-afe2-7cda88434107.png)

## Usage

```web
## Rest API endpoint is mapped to http://127.0.0.1:6035/

GET    http://127.0.0.1:6035/servers           - Get all the servers.
GET    http://127.0.0.1:6035/server/{name}     - To get server by name.
PUT    http://127.0.0.1:6035/servers/new       - To add from curl/postman. 
PUT    http://127.0.0.1:6035/servers/create    - To add from webpage.
DELETE http://127.0.0.1:6035/servers/{id}      - To Delete the server by name.
PUT    http://127.0.0.1:6035/servers/edit/{id} - To edit the server by name/id.
```
## Screenshots


## Using Web UI Forms
![createserver](https://user-images.githubusercontent.com/64476159/163685021-ef5cfa85-74f2-4854-b96c-33b5e9f635bd.gif)
![delete-update](https://user-images.githubusercontent.com/64476159/163685029-21b1e87b-db53-43c9-afef-9aba3347fb89.gif)

![finding resources by name](https://user-images.githubusercontent.com/64476159/163684997-aa57ed12-dca0-475a-bce6-af7f9680ea1c.png)

