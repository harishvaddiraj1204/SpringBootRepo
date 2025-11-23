# Use an official OpenJDK runtime as a base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
