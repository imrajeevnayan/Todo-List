
# Stage 1: Build the Spring Boot application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/static/ /app/static/
EXPOSE 10000
ENV PORT=10000
ENTRYPOINT ["java", "-jar", "app.jar"]
