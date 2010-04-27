class PersistenceWriter{
    def baseDir, fileName;
    PersistenceWriter(baseDir, fileName){
        this.baseDir=baseDir;
        this.fileName=fileName;
    }
    void write(tc){        
        File menuFile=new File(baseDir,fileName)
        def allLines = menuFile.readLines()
        def changedLines=[]
        
        String beanlikeDomain=tc.beanlike(tc.domain)
        String classlikeDomain=tc.classlike(tc.domain)
        
        allLines.each{line ->
            if(line.contains("</exclude-unlisted-classes>")){
                changedLines.add("    <class>"+tc.packageName+".app.domain."+classlikeDomain+"</class>")
            }
            changedLines.add(line)
        }
        menuFile.withWriter{file ->
            changedLines.each{line ->
                file.writeLine(line)
            }
        }
    }
}