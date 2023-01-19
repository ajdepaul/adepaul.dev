FROM docker.io/amazoncorretto:19-alpine3.17
COPY build/libs/website-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
