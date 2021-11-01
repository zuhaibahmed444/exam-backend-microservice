FROM openjdk:11
VOLUME /tmp
COPY target/paymentService-0.0.1-SNAPSHOT.jar paymentService.jar
ENTRYPOINT ["java","-jar","paymentService.jar"]