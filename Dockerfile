FROM tomcat:9.0.58-jdk17-openjdk
COPY target/servlets-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]