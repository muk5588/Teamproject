package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* *..controller..*.*(..)) || execution(* *..service..*.*(..))")
	public void log(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		logger.info("--실행=> {}", signature.toShortString());
		
	}
}
