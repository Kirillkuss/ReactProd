FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} react.jar
ENTRYPOINT ["java","-jar","react.jar"]
EXPOSE 8082:8080