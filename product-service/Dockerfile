# product-service/Dockerfile (similar structure)
FROM maven:3.8.6-amazoncorretto-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/product-service-*.jar /app/product-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "product-service.jar"]