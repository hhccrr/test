package com.hcr.springbeanlive.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LuBanAspect {

    @DeclareAnnotation("")
    private Man xxx;

    @Around("execution(* com.hcr.springbeanlive.service.UserService.test())")
    public String invoke(ProceedingJoinPoint point){

        try {
            System.out.println("aop");
            return (String) point.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }

}
