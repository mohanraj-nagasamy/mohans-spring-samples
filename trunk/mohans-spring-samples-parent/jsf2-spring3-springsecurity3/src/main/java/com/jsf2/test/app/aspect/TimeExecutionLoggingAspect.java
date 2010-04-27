package com.jsf2.test.app.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author Dimitar Makariev
 */
@Aspect
public class TimeExecutionLoggingAspect {

//    @Around("execution(* com.jsf2.test..web.*.*(..)) ")
//    @Around("execution(* com.jsf2.test..*.*(..))")
//    @Around("com.jsf2.test.app.aspect.SystemArchitecture.inWebControllers()")
//    @Around("com.jsf2.test.app.aspect.SystemArchitecture.inRepositories()")
//    @Around("com.jsf2.test.app.aspect.SystemArchitecture.inServices()")
    @Around("com.jsf2.test.app.aspect.SystemArchitecture.inSecured()")
    public Object log(ProceedingJoinPoint call) throws Throwable {

        Logger log = Logger.getLogger(TimeExecutionLoggingAspect.class.getName());
        final String methodName = call.toShortString();

        log.warning(" method [" + methodName + "] started");
        long begin = System.currentTimeMillis();
        Object point = call.proceed();
        long time = System.currentTimeMillis() - begin;
        log.warning(" method [" + methodName + "] executed for: " + time + "ms");
        return point;
    }
}
