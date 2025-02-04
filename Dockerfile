FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
# EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
# ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"] # 설정파일 분리
