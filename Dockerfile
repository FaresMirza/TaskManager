# Use the Jelastic Maven image with OpenJDK 21 to build the project
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use the official OpenJDK 21 image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]