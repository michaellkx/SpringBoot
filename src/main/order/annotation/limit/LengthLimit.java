package main.order.annotation.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//此注解用于限制字段进行手动赋值时的长度。注意:这是一个生命周期为“源码”级别的注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface LengthLimit {
    //int[] value() default {0,12} ;
}
