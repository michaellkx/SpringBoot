package main.order.annotation.authority;

import main.order.entity.LoginVO;
import main.order.sys.ThreadLocalUse;
import main.order.tool.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

//对加了@NeedSuper注解的方法进行权限的验证处理
@Aspect
@Component
public class PressmionImpl {

    @Pointcut(value = "execution(public * main.order.controller.*.*(..))")
    public void pointCutMethod(){

    }

    @Before(value = "pointCutMethod()")
    public void judageAuthority(JoinPoint joinPoint) throws MyException{

        try{

            if(joinPoint == null
                    || joinPoint.getTarget() == null
                    || joinPoint.getSignature() == null){
                return;
            }
            String methodName = joinPoint.getSignature().getName();
            Class<?> aclass = joinPoint.getTarget().getClass();

            HasPressmion annotation;
            try{
                Method method = aclass.getMethod(methodName);
                annotation = method.getAnnotation(HasPressmion.class);  //如果通过此方法对象获取不到@HasPressmion注解,则不做处理,允许下游方法执行
            }catch (Exception e){
               return;
            }
            if(annotation == null){
                return;
            }

            LoginVO user =(LoginVO) ThreadLocalUse.getThreadLocalCase().get();
            if(user == null){
                throw new MyException("用户掉线");
            }

            String[] values = annotation.value();
            for(String v : values){
                if(v.equals(user.getAuthority())){
                    return; //如果此被@HasPressmion注解修饰的方法的value含有“Super”,则允许下游方法执行
                }
            }

            throw new MyException("此用户"+user.getUsername()+"无"+methodName+"的权限");

        }catch (Exception e){
            throw new MyException("@HasPressmion注解实现类出错:"+e.getMessage());
        }

    }





}
