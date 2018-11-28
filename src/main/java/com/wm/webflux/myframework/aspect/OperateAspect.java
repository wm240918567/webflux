package com.wm.webflux.myframework.aspect;

import com.wm.webflux.myframework.commons.Constants;
import com.wm.webflux.myframework.target.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * MyLog注解功能实现
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Slf4j
public class OperateAspect {

    /**
     * 定义切点
     */
    @Pointcut(Constants.MYLOG_POINTCUT)
    public void annotationPointCut() { }

    /**
     * 切点前置操作
     * @param joinPoint 获取切点属性
     */
    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        log.info("打印前置日志:{}",annotation.value());
    }
}
