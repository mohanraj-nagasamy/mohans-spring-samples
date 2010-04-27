class MenuWriter{
    def baseDir, fileName;
    MenuWriter(baseDir, fileName){
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
            if(line.contains("</app:menu-form>")){
                changedLines.add("    <app:menu-group groupName=\"GEN ADDED - "+classlikeDomain+" \">")
                changedLines.add("        <app:menu-item itemOutcome=\"/secured/"+beanlikeDomain+"/list\" itemValue=\"#{msg['"+beanlikeDomain+".view.list']}\"/>")
                changedLines.add("        <app:menu-item itemOutcome=\"/secured/"+beanlikeDomain+"/form\" itemValue=\"#{msg['"+beanlikeDomain+".view.form']}\"/>")
                changedLines.add("    </app:menu-group>")
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