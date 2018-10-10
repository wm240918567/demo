package com.example.demo.target;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统计
 */
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Statistical {

    //操作名
    @NotBlank
    String name();

    //添加一次
    int addOne() default 1;

}
