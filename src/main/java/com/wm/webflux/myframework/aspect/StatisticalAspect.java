package com.wm.webflux.myframework.aspect;

import com.wm.webflux.myframework.commons.Constants;
import com.wm.webflux.myframework.target.Statistical;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Statistical注解功能实现
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class StatisticalAspect {

    /**
     * 记录数据 初始数据{@value}
     *
     */
    public static int NUM = 0;

    /**
     * 访问记录切点
     */
    @Pointcut(Constants.STATISTICAL_POINTCUT)
    public void addNumPointCut() { }

    /**
     * 切点前置操作
     */
    @Before("addNumPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        Statistical annotation = method.getAnnotation(Statistical.class);
        NUM = NUM +annotation.addOne();
        log.info("第{}次访问了{}方法",NUM,annotation.name());
    }
}
