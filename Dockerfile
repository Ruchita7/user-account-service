FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]