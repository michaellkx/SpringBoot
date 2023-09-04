package main.order.annotation.comment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解  用于使用中文描述一个字段
//实现类在aop包
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Comment {
    String value() default "";
}
