FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app-servicoSaude.jar
ENTRYPOINT ["java", "-jar", "/app-servicoSaude.jar"]
EXPOSE 8082