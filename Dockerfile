FROM tomcat:8.0.20-jre8
COPY target/onlinebookstore-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/onlinebookstore-0.0.1-SNAPSHOT.war
