JSF 2.0 with Maven 2 plugins for Glassfish v3, Jetty and Tomcat

The code has been downloaded from the following link-
1) Link ::-> http://dmakariev.blogspot.com/2009/12/jsf-20-with-maven-2-plugins-for.html

Running different servers
# * mvn package tomcat:run-war  
# * mvn package jetty:run-war  
# * mvn package -Pglassfish
# * mvn -Pglassfish embedded-glassfish:run

mvn clean compile war:inplace tomcat:run -> this is runing in exploded mode.