# Stage 1: Build the JAR using Gradle with JDK 22
FROM gradle:jdk22 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon  # This generates the JAR in /app/build/libs/

# Stage 2: Run the JAR
FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY --from=build /app/build/libs/To-Do-List-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]