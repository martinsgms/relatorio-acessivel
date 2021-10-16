FROM openjdk:11
ENV RA_ENV=dev
ENV RA_CONFIG_SERVER_URI=http://svc-config-server
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app-usuario.jar
ENTRYPOINT ["java", "-jar", "/app-usuario.jar"]
EXPOSE 8081