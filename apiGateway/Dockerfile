FROM openjdk:11
VOLUME /tmp
COPY target/apiGateway-0.0.1-SNAPSHOT.jar examApiGateway.jar
ENTRYPOINT ["java","-jar","examApiGateway.jar"]