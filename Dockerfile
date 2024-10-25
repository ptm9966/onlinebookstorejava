FROM ubuntu

RUN apt update
RUN apt install default-jdk -y
RUN apt update
RUN apt install git -y
RUN git clone https://github.com/Hassan-khan-007/onlinebookstore.git
RUN apt update
RUN apt install maven -y
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$PATH:$MAVEN_HOME/bin
RUN ls
WORKDIR onlinebookstore
RUN ls
RUN mvn clean
RUN mvn install
WORKDIR /
RUN ls
RUN mkdir /opt/tomcat
WORKDIR /opt/tomcat
ADD https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.tar.gz .
RUN tar -xvzf apache-tomcat-9.0.96.tar.gz
RUN mv apache-tomcat-9.0.96/* /opt/tomcat
EXPOSE 8080
RUN ls -l
WORKDIR /onlinebookstore/target
RUN ls -l
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
