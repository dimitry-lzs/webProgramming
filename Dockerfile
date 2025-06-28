# Multi-stage build for minimal size
FROM maven:3.9-eclipse-temurin-21 AS builder

# Copy source code
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage with Tomcat
FROM tomcat:9.0-jdk21-openjdk-slim

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR file
COPY --from=builder /app/target/vietnam.war /usr/local/tomcat/webapps/ROOT.war

# Configure Tomcat to use port 8888
RUN sed -i 's/port="8080"/port="8888"/g' /usr/local/tomcat/conf/server.xml

# Create directory for SQLite database with proper permissions
RUN mkdir -p /usr/local/tomcat/data && \
    chmod 755 /usr/local/tomcat/data

# Set working directory where SQLite DB will be created
WORKDIR /usr/local/tomcat/data

# Expose the new Tomcat port
EXPOSE 8888

# Start Tomcat
CMD ["catalina.sh", "run"]
