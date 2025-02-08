# user-service/Dockerfile
FROM maven:3.8.6-amazoncorretto-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests  # Build the JAR

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/user-service-*.jar /app/user-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-service.jar"]