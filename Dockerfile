# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image using distroless Java
FROM gcr.io/distroless/java21-debian12
#FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/Java_Application-1.0-SNAPSHOT.jar app.jar
COPY --from=build /app/target/libs libs

# Install a shell for debugging
#RUN apt-get update && apt-get install -y --no-install-recommends \
 #   curl \
  #  && rm -rf /var/lib/apt/lists/*

# Expose the port your application runs on
EXPOSE 9098

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]