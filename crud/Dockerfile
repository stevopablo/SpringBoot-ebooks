FROM openjdk:21-sdk-slim

ARG JAR_FILE=target/crud-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} crud_books.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "crud_books.jar"]