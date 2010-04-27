package com.jsf2.test.app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author Dimitar Makariev
 */
@Aspect
public class SystemArchitecture {

    @Pointcut("within(com.jsf2.test..*)&&@within(javax.annotation.ManagedBean)")
    public void inWebControllers() {}

    @Pointcut("within(com.jsf2.test..*)&&@within(org.springframework.stereotype.Repository)")
    public void inRepositories() {}

    @Pointcut("within(com.jsf2.test..*)&&@within(org.springframework.stereotype.Service)")
    public void inServices() {}

    @Pointcut("within(com.jsf2.test..*)&&@annotation(javax.annotation.security.RolesAllowed)")
    public void inSecured() {}
}
