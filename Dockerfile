FROM openjdk:17
ADD target/Task-0.0.1-SNAPSHOT.jar app.jar
VOLUME /main.app
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8083