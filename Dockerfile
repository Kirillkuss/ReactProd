FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} reactpro.jar
ENTRYPOINT ["java","-jar","/reactpro.jar"]
EXPOSE 8082:8080