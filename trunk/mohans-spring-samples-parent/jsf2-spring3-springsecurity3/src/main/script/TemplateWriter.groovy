import groovy.text.SimpleTemplateEngine;

class TemplateWriter{
    def templateFile,targetFile,engine,binding;
    
    TemplateWriter(baseDir, targetDirNameTemplate, targetFileNameTemplate, TemplaterContext tc){        
        this.binding = ['tc' : tc]
        
        this.engine = new SimpleTemplateEngine()
        def targetDirTemplate = engine.createTemplate(targetDirNameTemplate).make(binding)
        
        File outputDir = new File( baseDir, targetDirTemplate.toString() )     
        outputDir.mkdirs()
        
        def targetFileTemplate = engine.createTemplate(targetFileNameTemplate).make(binding)
        this.targetFile = new File( outputDir, targetFileTemplate.toString() );
    }
    
    TemplateWriter template(templateDir, templateFileName){
        this.templateFile = new File( templateDir, templateFileName )
        if(!this.templateFile.exists()){
            fail("TemplateWriter templateFile :"+templateFile+" doesn't exist !" )
        }
        return this;
    }
    
    void ifTargetExistsFail(){
        if(this.targetFile.exists()){
             fail("TemplateWriter targetFile :"+this.targetFile+" already exists !");
        }    
    }
    void write(){        
        def template = engine.createTemplate(templateFile).make(binding);
        targetFile.write(template.toString());
    }    
}