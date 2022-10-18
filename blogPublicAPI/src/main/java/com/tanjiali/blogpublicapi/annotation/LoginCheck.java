package com.tanjiali.blogpublicapi.annotation;

import java.lang.annotation.*;

/**
 * @ClassName 登录检测注解
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/16 21:15
 * @Version 1.0
 **/
@Target({ElementType.METHOD,ElementType.TYPE}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface LoginCheck {
    String value() default "";
}
