FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.5_8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
