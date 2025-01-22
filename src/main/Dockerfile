
# Use an official Maven image to build the project
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY . .

# Build the project using Maven
RUN mvn clean install 

FROM tomcat:9.0.74-jdk17

# Set environment variables for Tomcat
ENV CATALINA_HOME /usr/local/tomcat
ENV CATALINA_BASE /usr/local/tomcat

#RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your application's WAR file into the Tomcat webapps directory
# Replace "your-application.war" with the actual file name of your WAR file
#COPY your-application.war $CATALINA_HOME/webapps/your-application.war

COPY --from=build /app/target/onlinebookstore.war $CATALINA_HOME/webapps/ROOT.war

# Expose port 8080 to allow access to the application
EXPOSE 8080

# Start Tomcat server in the foreground
CMD ["catalina.sh", "run"]
