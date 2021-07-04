package sky.lib.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExampleAop {
	private static final Logger logger = LoggerFactory.getLogger(ExampleAop.class);//log찍기위해 
	
	/*@Pointcut("execution(* sky.**.web.*Controller.*(..))")*/
	@Pointcut("within(sky.main.web.*)")
	public void aspectMethod(){
		
	}
	/*@Before("aspectMethod()")
	public void beforeMethod(JoinPoint joinPoint){
		System.out.println("사용자의 요청 : "+joinPoint.getTarget());
		logger.info("사용자의 요청 : "+joinPoint.getTarget());
		logger.info("사용자의 요청 : "+joinPoint.getTarget());
	}*/
	
	@AfterThrowing(pointcut="aspectMethod()", throwing="exception")
	public void afterExceptionMethod(JoinPoint joinPoint, Exception exception){
		System.out.println("사용자의 요청"+joinPoint.getTarget());
		logger.error("ST에러발생=========================");
		logger.error("에러위치 : "+exception.getClass());
		logger.error("에러내용 : "+exception.getMessage());
		logger.error("ED에러발생=========================");
	}
	
	/*@Around("aspectMethod()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		//시간 
		long st = System.currentTimeMillis();
		//핵심기능
		Object rtn = joinPoint.proceed();
		//시간
		long ed = System.currentTimeMillis();
		System.out.println("걸린시간: "+(ed-st));
		System.out.println("Object : "+rtn);
		return rtn;
	}*/
	
	
}
