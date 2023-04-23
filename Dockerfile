FROM openjdk:17-jdk
COPY ./target/crud-rest-java-mysql-docker-2023-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
