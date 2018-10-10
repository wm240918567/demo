package com.example.demo.aspect;

import com.example.demo.target.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class OperateAspect {


    @Pointcut("@annotation(com.example.demo.target.MyLog)")
    public void annotationPointCut() { }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        System.out.println("打印：" + annotation.value() + " 前置日志");
    }
}
