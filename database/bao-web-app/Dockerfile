FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /build/libs/bao-web-app-0.0.1-SNAPSHOT.jar app.jar
ADD . app/
ENTRYPOINT ["app/gradlew","build","&&","java","-jar","app.jar"]