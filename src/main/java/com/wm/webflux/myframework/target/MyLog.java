package com.wm.webflux.myframework.target;

import java.lang.annotation.*;

/**
 * 自定义注解MyLog
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    /**
     * @return 日志内容
     */
    String value();
}
