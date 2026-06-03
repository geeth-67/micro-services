FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests
EXPOSE 8081
ENTRYPOINT["java", "-jar", "target/gem-service-1.0.0.jar"]