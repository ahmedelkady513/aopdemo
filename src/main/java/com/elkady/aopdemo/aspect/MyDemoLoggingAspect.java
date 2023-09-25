package com.elkady.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    
    @Pointcut("execution(* com.elkady.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.elkady.aopdemo.dao.*.get*(..))")
    private void getter() {}
    
    @Pointcut("forDaoPackage() && !(getter())")
    private void forDaoPackageNoGetter() {}

    @Before("forDaoPackageNoGetter()")
    public void  beforeDaoMethoddvice(JoinPoint theJoinPoint) {
        System.out.println("===> before Method");

        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method : " + methodSignature);

        Object[] args = theJoinPoint.getArgs();

        for(Object tempArg : args) {
             System.out.println(tempArg);

             if(tempArg instanceof Boolean) {
                boolean test = (Boolean) tempArg;

                System.out.println("boolean value " + tempArg);
             }
        }

    }
}
