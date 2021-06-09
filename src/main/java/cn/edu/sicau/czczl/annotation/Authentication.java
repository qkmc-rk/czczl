package cn.edu.sicau.czczl.annotation;


import cn.edu.sicau.czczl.annotation.constant.AuthAopConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author qkmc
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Authentication {

    /**
     * true为启用验证
     * false为跳过验证
     * @return
     *
     */
    boolean pass() default true;

    int count() default 5;

    AuthAopConstant role() default AuthAopConstant.ANON;
}
