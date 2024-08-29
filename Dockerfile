FROM openjdk:22-jdk-slim

COPY ./build/libs/Vote-0.0.1-SNAPSOHT.jar app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
