# Use Java runtime
FROM eclipse-temurin:17-jdk

# Copy jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the app
ENTRYPOINT ["java","-jar","/app.jar"]