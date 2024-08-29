#FROM openjdk:22
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENV TZ=Asia/Seoul
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar","-Duser.timezone=Asia/Seoul"]

#FROM openjdk:22-slim
#
#COPY ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:22-slim

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]