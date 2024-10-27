# Use a GraalVM JDK image to build the project
FROM ghcr.io/graalvm/graalvm-ce-java17:21.0.5 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM ghcr.io/graalvm/graalvm-ce-java17:21.0.5
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]