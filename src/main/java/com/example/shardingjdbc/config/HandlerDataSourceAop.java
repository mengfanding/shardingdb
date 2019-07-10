package com.example.shardingjdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Order(1)
public class HandlerDataSourceAop {

    //切面到service是为了方便从service层的方法上拿到参数判断选择的库
    @Pointcut("execution(public * com.example.shardingjdbc.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {

        HandlerDataSource.putDataSource("defaultDb");

    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        HandlerDataSource.clear();
    }

}