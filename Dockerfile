FROM openjdk:18-jdk-alpine

COPY server2api-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 6039

EXPOSE 27017

ENTRYPOINT ["java","-jar","/app.jar"] 
