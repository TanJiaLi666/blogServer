package com.tanjiali.blogpublicapi.annotation;

import java.lang.annotation.*;

/**
 * @ClassName 访问日志
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/16 16:58
 * @Version 1.0
 **/
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface VisitLog {
    String value() default "";
}
