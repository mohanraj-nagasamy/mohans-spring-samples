import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceInvocationLogger {

	private int invocationCount;

	@Pointcut("execution(* blog.*Service+.*(..))")
	public void serviceInvocation() {
		System.out.println("ServiceInvocationLogger.serviceInvocation()");
	}

	@Before("serviceInvocation()")
	public void log() {
		invocationCount++;
		System.out.println("service invocation #" + invocationCount);
	}

	@Before("serviceInvocation()")
	public void logMethodEntry(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String name = joinPoint.getSignature().toLongString();
		StringBuffer sb = new StringBuffer(name + " called with: [");
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			sb.append(o);
			sb.append((i == args.length - 1) ? "]" : ", ");
		}
		System.out.println(sb);
	}

	@AfterReturning(pointcut = "serviceInvocation()", returning = "result")
	public void logMethodExit(StaticPart staticPart, Object result) {
		String name = staticPart.getSignature().toLongString();
		System.out.println(name + " returning: [" + result + "]");
	}
}