package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAdvice.class);
	
	// pointCut 설정을 위한 의미 없는 메소드
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
	}
	
	@Before("dummy()") // pointCut 을 메소드 이름문자열로 주면 됨
	public void beforeMethod(JoinPoint joinPoint) {
		
		logger.debug("beforeMethod : {}", joinPoint.getSignature().getName());
		
	}
	
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		//ProceedingJoinPoint 인터페이스 :  메소드를 실행할 수 있는 메소드를 가지고 있음
		// joinPoint : 핵심 기능을 담고있는 메소드 로직
		
		
		
		// 메소드 실행 전 공통 관심사항
		long start = System.currentTimeMillis();
		
		// 메소드 실행
			// 메소드 실행을 위한 인자 필요할 때 있음 : joinPoint.getArgs(); -> 문자열 배열로 인자들을 리턴해줌
		Object ret = joinPoint.proceed(joinPoint.getArgs());
		
		// 메소드 실행 후 공통 관심사항
		long end = System.currentTimeMillis();
		logger.debug("profiling : {} {} - {}" , joinPoint.getSignature().getDeclaringTypeName(),
												joinPoint.getSignature().getName(),
												end-start + " ms");
		
		return ret;
	}
}
