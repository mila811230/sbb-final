package com.mysite.sbbfinal.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {
	
	// * 모든 반환타입
	// kr.co.cofile.hdcdmybatis. 대상 패키지
	// *Greet Greet로 끝나는 모든 클래스
	// .* 모든 메서드
	// (..) 모든 인수값
	@Before("execution(* com.mysite.sbbfinal.controller.*Greet.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("=== Before Advice ===");

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sdf.format(today));

		System.out.println("Method: " + joinPoint.getSignature().getName());
	}
	
	@Before("execution(* com.mysite.sbbfinal.favorite.controller.Favorite*.*(..))")
	public void favoriteBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("=== Before Advice ===");

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sdf.format(today));

		System.out.println("Method: " + joinPoint.getSignature().getName());
	}
}