package com.estsoft.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureDaoExecutionTimeAspect {
	
	@Around("execution(* *..dao.*.*(..))||execution(* *..service.*.*(..))||execution(* *..control.*.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
	
		Object vo = pjp.proceed();
				
		stopWatch.stop();
		
		String taskName = pjp.getTarget().getClass()+"."+pjp.getSignature().getName();
		System.out.println("[Execution Time]["+taskName+"]:"+stopWatch.getTotalTimeMillis());
		
		return vo;
	}
}
