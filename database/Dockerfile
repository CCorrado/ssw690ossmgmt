FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /build/libs/database-0.0.1.jar app.jar
COPY . app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]