package com.wm.webflux.myframework.target;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解Statistical
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Statistical {

    /**
     * @return 操作名
     */
    @NotBlank
    String name();

    /**
     * 添加
     * @return 添加次数 默认1
     */
    int addOne() default 1;

}
