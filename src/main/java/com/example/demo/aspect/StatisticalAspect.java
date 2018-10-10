package com.example.demo.aspect;

import com.example.demo.target.Statistical;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class StatisticalAspect {

    public static int NUM = 0;

    @Pointcut("@annotation(com.example.demo.target.Statistical)")
    public void addNumPointCut() { }

    @Before("addNumPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        Statistical annotation = method.getAnnotation(Statistical.class);
        log.info("======annotation.addOne():"+annotation.addOne());
        log.info("======NUM:"+NUM);
        NUM = NUM +annotation.addOne();
        log.info("======相加后:"+NUM);

        log.info("第{}次访问了{}方法",NUM,annotation.name());
    }
}
