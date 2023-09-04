package main.order.annotation.autofill;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//此注解用于操作数据库时,对为null的字段进行默认值"~"填充
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefAutoFillValue {
    String defaultString() default "自定义注解的默认字段";

}
