package com.jsf2.test.common.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * this aspect 'fixes' the com.sun.faces.config.WebConfiguration  canProcessJndiEntries() method to always return false.
 * this is needed because of a bug in Google Application Engine development environment
 * 
 * @author Dimitar Makariev
 */
@Aspect
public class FixWebConfigurationAspect {

    @Around("execution(* com.sun.faces.config.WebConfiguration.canProcessJndiEntries())")
    public Object returnFalse(ProceedingJoinPoint call) throws Throwable {
        Logger log = Logger.getLogger("com.jsf2.test.common.aspect.FixWebConfigurationAspect");
        final String methodName = call.toShortString();
        log.warning(" method [" + methodName + "] call 'fixed' ");
        return false;
    }
}
