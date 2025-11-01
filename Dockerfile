  # Use a base image with Java installed
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
# Assuming your Spring Boot application builds a JAR named target/your-application-name.jar
COPY target/spring-boot_rest_api-1.0-SNAPSHOT.jar spring-boot_rest_api.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8085

# Command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "spring-boot_rest_api.jar"]