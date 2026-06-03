FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y maven && mvn clean install -DskipTests
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "target/dealer-service-0.0.1-SNAPSHOT.jar"]