FROM gradle:7.3.3-jdk11 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN gradle clean build --exclude-task test

# 두 번째 스테이지: 실행 가능한 JAR 파일을 생성하여 Spring Boot 애플리케이션 실행
FROM adoptopenjdk:11-jre-hotspot AS final
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]