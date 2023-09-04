package main.order.annotation.authority;


import main.order.tool.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//此注解放在需要超级管理员权限的方法上
//实现类在aop包
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface HasPressmion {

    String[] value() default {Constant.NORMAL};

}
