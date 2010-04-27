class LabelWriter{
    def baseDir, fileName;
    LabelWriter(baseDir, fileName){
        this.baseDir=baseDir;
        this.fileName=fileName;
    }
    void write(tc){
        File msgFile=new File(baseDir,fileName)
        String beanlikeDomain=tc.beanlike(tc.domain)
        String classlikeDomain=tc.classlike(tc.domain)
        msgFile.append("\n#labels generated for "+classlikeDomain)
        msgFile.append("\n"+beanlikeDomain+".view.detail=GEN "+classlikeDomain+" detail")
        msgFile.append("\n"+beanlikeDomain+".view.form=GEN "+classlikeDomain+" form")
        msgFile.append("\n"+beanlikeDomain+".view.list=GEN "+classlikeDomain+" list")
        tc.fields.each{f->
            msgFile.append("\n"+beanlikeDomain+"."+tc.beanlike(f)+"=GEN "+tc.classlike(f))
        }
    }
}