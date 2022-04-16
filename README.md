# Server-2-API-Kube

An Kubernetes deployment configuration for the Java Rest API 2 Server/Web-UI & MongoDB which is a project based Java Rest API CURD functionalities to Create, Delete, Get server objects using json-encoded message body(postman) or using Web UI. 


# Tech Stack

* minikube
* Docker



Given Kubernetes deployment kustomization.yaml outlined below.

```
resources:
  - create_sc.yaml
  - mongovolume.yaml
  - mongoVolumeClaim.yaml
  - mongoService.yaml
  - mongo.yaml
  - restapiService.yaml
  - restapi-app.yaml
```

## Running the Application

```bash
kubectl apply -k kustomization.yaml
```
## Docker Build
* Creating a Dockerfile for restapi-app Deployment.
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
docker push furqano/apprestapi-app:latest
```

## Screenshots
### Storage-Class

### Persistent Volume for MongoDB 

### Persistent Volume Claim for MongoDB 

### Service for MongoDB 

### MongoDB Deployment 

### Restapi-app Deployment

### Restapi-app Service

## Connect to Application Using Port-Forward 

```
kubectl port-forward svc/rest-api-app-service 6035:6035
```
## Connect to Application Using NodePort 

```
$ kubectl get svc 

rest-api-app-service   NodePort    10.105.194.142   <none>        6035:32129/TCP

$minikube ip

192.168.49.2

http://192.168.49.2:32129/servers
```
