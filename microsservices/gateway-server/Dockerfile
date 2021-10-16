FROM openjdk:11
ENV RA_ENV=dev
ENV RA_CONFIG_SERVER_URI=http://svc-config-server
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /gateway-server.jar
ENTRYPOINT ["java", "-jar", "/gateway-server.jar"]
EXPOSE 5555