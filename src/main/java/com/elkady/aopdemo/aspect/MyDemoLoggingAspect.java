package com.elkady.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    public void  beforeDaoMethoddvice() {
        System.out.println("===> before Method");
    }
}
