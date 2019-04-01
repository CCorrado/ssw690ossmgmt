FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /build/libs/database-0.0.1.jar app.jar
ADD . app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]