FROM amazoncorretto:21.0.2-alpine3.18
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
