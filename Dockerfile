# Stage 1: Build the Spring Boot app using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first (better caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

COPY uploads ./uploads

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Runtime (lightweight image)
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

COPY --from=builder /app/uploads ./uploads


# Expose port (Render overrides this automatically)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
