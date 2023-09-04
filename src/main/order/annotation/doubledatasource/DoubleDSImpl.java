package main.order.annotation.doubledatasource;

import main.order.controller.productController;
import main.order.tool.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//判断双数据库标识的实现类
@Aspect
@Component
@Primary
@Order(value = 1)   ////值越小的注解将会先执行，而值越大的注解将会后执行。如果没有指定@Order()的值，默认按照注解的定义顺序执行
public class DoubleDSImpl {

    @Pointcut(value = "execution(public * main.order.controller.*.*(..))")  //拦截所有controller包下的方法
    public void pointCut(){
    }

    //选择指定数据库
    @Before(value = "pointCut()")
    public void swicthDS(JoinPoint joinPoint)throws MyException{

        String flag = DataSourceType.MYSQL.name();
        try{
            if(joinPoint == null
                    || joinPoint.getTarget() == null
                    || joinPoint.getSignature() == null){
                throw new MyException("切点入参为空");
            }

            Class<? extends java.lang.Object> aClass = joinPoint.getTarget().getClass();
            if(aClass.equals(productController.class)){ //如果是此类,修改数据源标志符
                flag = DataSourceType.ORACLE.name();
            }

        }catch (Exception e){
            throw new MyException("aop选择数据源失败"+e.getMessage());
        }finally {
            DynamicDS.flag.set(flag);   //切换数据源的动作
        }

    }




}
