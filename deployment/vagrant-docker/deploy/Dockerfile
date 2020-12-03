FROM tomcat
USER root
# Adding users
COPY tomcat-users.xml /usr/local/tomcat/conf/
# Download missing files
RUN wget https://mirror.ibcp.fr/pub/apache/tomcat/tomcat-9/v9.0.39/bin/apache-tomcat-9.0.39.tar.gz -P temp \
&& tar xf temp/apache-tomcat-9*.tar.gz -C temp \
&& cp -r temp/apache-tomcat-9.0.39/webapps/* /usr/local/tomcat/webapps/
# Authorising access to manager
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/
CMD ["catalina.sh","run"]
