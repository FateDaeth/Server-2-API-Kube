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
```
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: local-storage
provisioner: kubernetes.io/no-provisioner
volumeBindingMode: WaitForFirstConsumer
reclaimPolicy: Retain
```
![Kube-SC](https://user-images.githubusercontent.com/64476159/163685277-fd7fdfe4-60ce-47c1-8030-32dc8a82200e.png)


### Persistent Volume for MongoDB 
```
kind: PersistentVolume
apiVersion: v1
metadata:
  name: task-pv-volume
  labels:
    type: local
spec:
  storageClassName: mongo
  accessModes:
    - ReadWriteOnce
  capacity:
    storage: 1Gi
  hostPath:
    path: /data/mongo1
    type: DirectoryOrCreate
```
![kube-Mongo-PV](https://user-images.githubusercontent.com/64476159/163685287-cb03fcfd-63aa-45fb-ad29-ef17c04afc2a.png)

### Persistent Volume Claim for MongoDB 
```
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mongo-pv-claim
  labels:
    app: mongopv
spec:  
  accessModes:
    - ReadWriteOnce
  storageClassName: mongo
  resources:
    requests:
      storage: 1Gi
```
![mogo-pvc](https://user-images.githubusercontent.com/64476159/163685310-bd77654f-5036-40d9-a617-7a99cfdbe8cc.png)

### Service for MongoDB 
```
apiVersion: v1
kind: Service
metadata:
  name: mongodb
  labels:
    app: mongodb
spec:
  ports:
  - port: 27017
    protocol: TCP
  selector:
    appdb: mongodb
```
![kube-mongo-service](https://user-images.githubusercontent.com/64476159/163685325-9b21f636-ae06-45bb-9fac-f9f4a0743d09.png)

### MongoDB Deployment 
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongodb
  labels:
    appdb: mongodb
spec:
  replicas: 1
  selector:
    matchLabels:
      appdb: mongodb
  template:
    metadata:
      labels:
        appdb: mongodb
    spec:
      containers:
      - name: mongodb
        image: mongo:3.6.6
        ports:
        - containerPort: 27017              
        volumeMounts:
        - name: hostvol
          mountPath: /data/db
      volumes:
      - name: hostvol
        persistentVolumeClaim:
          claimName: mongo-pv-claim
```
![kube-mongo-app](https://user-images.githubusercontent.com/64476159/163685329-7dfd874a-74da-4161-b17a-1ccc71a9d6ec.png)

### Restapi-app Deployment
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: restapi-app
  labels:
    app: restapi-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: restapi-app
  template:
    metadata:
      labels:
        app: restapi-app
    spec:
      containers:
      - name: restapi-app
        image: furqano/restapi-app:latest
        env:
        - name: MONGODB_HOST
          value: mongodb
        ports:
        - containerPort: 6035
```

### Restapi-app Service
```
apiVersion: v1
kind: Service
metadata:
  name: rest-api-app-service
  labels:
    run: rest-api-app-service
spec:
  type: NodePort
  ports:
  - port: 6035
    protocol: TCP
  selector:
    app: restapi-app
```
## Connect to Application Using Port-Forward 

```
kubectl port-forward svc/rest-api-app-service 6035:6035
```
![port-fw ](https://user-images.githubusercontent.com/64476159/163685377-cec6506b-62cb-4be0-8477-5ce68885d42c.png)

## Connect to Application Using NodePort 

```
$ kubectl get svc 

rest-api-app-service   NodePort    10.105.194.142   <none>        6035:32129/TCP

$minikube ip

192.168.49.2

http://192.168.49.2:32129/servers
```
![kube-node-port](https://user-images.githubusercontent.com/64476159/163685371-34275bce-693d-4ed1-8444-44bc41ae3873.png)

