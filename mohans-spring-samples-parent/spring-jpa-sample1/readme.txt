1) update the followin in "app-config.properties" 
	client-stp-number=
	client-program-id=
2) Check file header indexes are correct in FileHeader.java  
3) $mvn package install
4) $java -jar .\target\conversion-1.0-SNAPSHOT.jar -fileName ./file/test1.csv -fileType access
5) $java -jar .\target\conversion-1.0-SNAPSHOT.jar -fileName ./file/test2.csv -fileType points