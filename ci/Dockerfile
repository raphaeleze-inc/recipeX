# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the rest of the project files into the container
COPY . .

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Run the build to create the JAR file
RUN ./mvnw clean package

# Expose the port on which the application runs
EXPOSE 8080

# Define the command to run the app using the JAR file
CMD ["java", "-jar", "target/recipex-0.0.1-SNAPSHOT.jar"]