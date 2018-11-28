package com.wm.webflux.myframework.commons;

/**
 * 常量
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
public class Constants {

    /**
     * spring boot 项目启动时要扫描注解的包 {@value}
     */
    public static final String START_SCAN_PACKAGES =  "com.wm.webflux";

    /**
     * Mylog注解切点 {@value}
     */
    public static final String MYLOG_POINTCUT =  "@annotation(com.wm.webflux.myframework.target.MyLog)";

    /**
     * Mylog注解切点 {@value}
     */
    public static final String STATISTICAL_POINTCUT =  "@annotation(com.wm.webflux.myframework.target.Statistical)";




}
