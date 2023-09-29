package com.elkady.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elkady.aopdemo.Account;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
    
    @AfterReturning(
        pointcut = "execution(* com.elkady.aopdemo.dao.AccountDAO.findAccounts(..))",
        returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @AfterReturn on method " + method);
        System.out.println("\n=====>>> Executing result is: " + result);

    }

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
