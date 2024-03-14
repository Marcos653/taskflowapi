FROM openjdk:17-slim

RUN apt-get update && apt-get install -y wget maven

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

RUN mv target/taskflowapi-0.0.1-SNAPSHOT.jar app.jar && rm -rf target

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
