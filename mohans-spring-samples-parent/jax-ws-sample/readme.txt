As I've used tomcat maven plugin, there are the following files need to be modified.

1) M2_HOME/conf/settings.xml
----------------------------
	<server>
      <id>mytomcat</id>
      <username>tomcat</username>
      <password>s3cret</password>
    </server>

2) TOMCAT_HOME/conf/tomcat-users.xml
------------------------------------
<tomcat-users>
  <role rolename="manager"/>
  <user username="tomcat" password="s3cret" roles="manager"/>
</tomcat-users>

3) start the tomcat -> $catalina.bat run
4) deploy the server project first -> $mvn tomcat:deploy
5) test the client project -> $mvn test