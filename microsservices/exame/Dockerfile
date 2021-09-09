FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app-exame.jar
ENTRYPOINT ["java", "-jar", "/app-exame.jar"]
EXPOSE 8080