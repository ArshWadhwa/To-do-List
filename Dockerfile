# Use OpenJDK 17 as the base image
# Use Java 22 (compatible with your compiled code)
FROM eclipse-temurin:22-jdk


# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the host machine into the container
COPY build/libs/To-Do-List-1.0-SNAPSHOT.jar app.jar


# Expose the port 8080
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
