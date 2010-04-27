class Generator{
    
    static void generate(project){
        def domain = project.properties['domain'];
        def fields = project.properties['fields'].split(",");
        def packageName = project.groupId
        def baseDir = project.basedir
        def templateDir = project.properties['templateDir']
        def packageDir = packageName.replace( '.', '/' )

        def javaDir = "src/main/java/"+packageDir+"/app/"

        def messagesFileName = "src/main/resources/Messages.properties"
        def menuFileName = "src/main/webapp/resources/app/menu.xhtml"
        def persistenceFileName = "src/main/resources/META-INF/persistence.xml"

        TemplaterContext tc = new TemplaterContext(domain:domain,fields:fields,packageName:packageName);
        def webDir = "src/main/webapp/secured/${tc.beanlike(tc.domain)}"

        TemplateWriter twDomain = new TemplateWriter(baseDir,javaDir+"domain","${tc.classlike(tc.domain)}.java",tc)
        .template(templateDir,"domain-java.template");
        TemplateWriter twRepository = new TemplateWriter(baseDir,javaDir+"repository","${tc.classlike(tc.domain)}Repository.java",tc)
        .template(templateDir,"repository-java.template");
        TemplateWriter twController = new TemplateWriter(baseDir,javaDir+"web","${tc.classlike(tc.domain)}Controller.java",tc)
        .template(templateDir,"controller-java.template");

        TemplateWriter twForm = new TemplateWriter(baseDir,webDir,"form.xhtml",tc)
        .template(templateDir,"form-xhtml.template");
        TemplateWriter twDetail = new TemplateWriter(baseDir,webDir,"detail.xhtml",tc)
        .template(templateDir,"detail-xhtml.template");
        TemplateWriter twList = new TemplateWriter(baseDir,webDir,"list.xhtml",tc)
        .template(templateDir,"list-xhtml.template");


        twDomain.ifTargetExistsFail();
        twRepository.ifTargetExistsFail();
        twController.ifTargetExistsFail();
        twForm.ifTargetExistsFail();
        twDetail.ifTargetExistsFail();
        twList.ifTargetExistsFail();

        twDomain.write();
        twRepository.write();
        twController.write();
        twForm.write();
        twDetail.write();
        twList.write();

        new LabelWriter(baseDir, messagesFileName).write(tc);
        new MenuWriter(baseDir, menuFileName).write(tc);
        new PersistenceWriter(baseDir, persistenceFileName).write(tc);
    }
}