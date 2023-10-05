package com.elkady.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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

    @Around("execution(* com.elkady.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out which method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get start time
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = "Bad Traffic";
        }
        // get end time
        long end = System.currentTimeMillis();

        // compute duration and print it
        long duration = end - begin;

        System.out.println("\n======> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.elkady.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (Finally) on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.elkady.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
        System.out.println("\n=====>>> The Exception is: " + theExc);

    }

    @AfterReturning(pointcut = "execution(* com.elkady.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @AfterReturn on method " + method);
        System.out.println("\n=====>>> Executing result is: " + result);

    }

    @Pointcut("execution(* com.elkady.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* com.elkady.aopdemo.dao.*.get*(..))")
    private void getter() {
    }

    @Pointcut("forDaoPackage() && !(getter())")
    private void forDaoPackageNoGetter() {
    }

    @Before("forDaoPackageNoGetter()")
    public void beforeDaoMethoddvice(JoinPoint theJoinPoint) {
        System.out.println("===> before Method");

        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method : " + methodSignature);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Boolean) {
                boolean test = (Boolean) tempArg;

                System.out.println("boolean value :" + tempArg);
            }
        }

    }
}
