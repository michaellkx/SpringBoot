package main.order.annotation.autofill;

import main.order.entity.baseVO.BaseVO;
import main.order.tool.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


//注解的功能一般是基于aop实现的
@Aspect
@Component(value = "AutoFillValueImpl")
public class DefAutoFillValueImpl {

    @Pointcut(value = "execution( public * main.order.controller.*.*(..) )")
    public void point(){
    }

    @After(value = "point()")
    public void doAction(JoinPoint joinPoint) throws MyException{

        try{

            if(joinPoint == null) throw new MyException("");

            Object[] args = joinPoint.getArgs();

            for(Object o : args){
                if(o instanceof BaseVO){
                    this.fillValueAction(o);
                }else if(o instanceof List && ((List<?>) o).get(0)!= null && ((List<?>) o).get(0) instanceof BaseVO){
                    List<BaseVO> oList = (List<BaseVO>) o;
                    for(BaseVO e:oList){
                        this.fillValueAction(e);
                    }
                }
            }

        }catch (Exception e){
            throw new MyException("使用反射实现注解(AutoFillValue)失败");
        }

    }

    //给EntityFatherVO对象字段的空值赋上默认值  动作
    private void fillValueAction(Object e) throws MyException{

        try{
            if(e == null)  return;
            Class thisClass = e.getClass();

            //此模板类有无我的自定义注解修饰   此模板类的父类有无自定义注解修饰
            boolean ifclassHasAnnotation = thisClass.getAnnotation(DefAutoFillValue.class) == null;
            boolean ifsuperClassHasAnnotation = thisClass.getSuperclass().getAnnotation(DefAutoFillValue.class) == null;

            Annotation annotation =
                    ifclassHasAnnotation ?
                            (
                                ifsuperClassHasAnnotation ?
                                        null
                                        : thisClass.getSuperclass().getAnnotation(DefAutoFillValue.class)
                            )
                            : thisClass.getAnnotation(DefAutoFillValue.class);

            if(annotation != null){

                Field[] declaredfields = thisClass.getDeclaredFields(); //此类的成员变量
                Field[] declaredSuperFields = thisClass.getSuperclass().getDeclaredFields();   //父类的成员变量
                ArrayList<Field> allFieldList = new ArrayList<>();  //此类“所有”的成员变量
                for(Field f:declaredfields){
                    allFieldList.add(f);
                }
                for(Field f:declaredSuperFields){
                    allFieldList.add(f);
                }

                for(Field f:allFieldList){
                    f.setAccessible(true);
                    if(f.get(e) == null && f.getType().equals(java.lang.String.class))
                        f.set(e,"~");   //对为String类型且为空值的字段赋值"~"
                }

            }

        }catch (Exception ex){
            throw new MyException(""+ex.getMessage());
        }


    }
}
