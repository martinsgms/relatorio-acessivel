FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app-usuario.jar
ENTRYPOINT ["java", "-jar", "/app-usuario.jar"]
EXPOSE 8081