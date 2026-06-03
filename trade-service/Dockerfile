FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests
EXPOSE 8083
ENTRYPOINT["java", "-jar", "target/trade-service-1.0.0.jar"]