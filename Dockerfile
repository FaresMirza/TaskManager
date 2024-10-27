# Use the official Maven image with JDK 21 to build the project
FROM maven:3.9.5-openjdk-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use the GraalVM JDK 21 image for running the application
FROM ghcr.io/graalvm/graalvm-ce-java21:21.0.5

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]