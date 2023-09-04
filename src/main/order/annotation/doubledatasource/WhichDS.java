package main.order.annotation.doubledatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//此注解用于表示使用那个数据库
@Target({ElementType.TYPE,ElementType.METHOD})  //修饰类或者方法
@Retention(RetentionPolicy.RUNTIME)
public @interface WhichDS {

    DataSourceType value() default DataSourceType.MYSQL;    //不添加此注解或者添加此注解不指定值为DataSourceType.oracle的话,默认使用mysql库

}
