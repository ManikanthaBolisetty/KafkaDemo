FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S springuser -G spring
USER springuser
WORKDIR /home/springuser
COPY target/KafkaDemo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]