package main.order.tool.exception;

import main.order.aop.log.LogUtils;
import main.order.aop.log.LogVO;
import main.order.tool.ResultTool;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;

//全局异常处理器 (记录异常信息+抛出异常)
@RestControllerAdvice
public class GlobalExceptionHandler {

    //专门处理 MyException异常(包括其子类)
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResultTool<String> handleException(MyException e) throws MyException {
        String mistakedata = e.getMessage();
        LogVO logVO = LogUtils.backlogVOWithError(mistakedata);
        LogUtils.writeLogToTxt(logVO);
        return new ResultTool<String>(mistakedata);
    }


    //专门处理 Exception异常(包括其子类)
    @ExceptionHandler(value = Exception.class)
    public ResultTool<String> handleException(Exception e) throws MyException {
        String mistakedata = e.getMessage()+e.getLocalizedMessage();
        LogVO logVO = LogUtils.backlogVOWithError(mistakedata);
        LogUtils.writeLogToTxt(logVO);
        return new ResultTool<String>(mistakedata);
    }


    //专门处理 java.lang.reflect.UndeclaredThrowableException异常(包括其子类)
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public ResultTool<String> handleException(UndeclaredThrowableException e) throws MyException {
        String mistakedata = null;
        try{
            Class<?> aClass = Class.forName("java.lang.reflect.UndeclaredThrowableException");
            Field[] fields = aClass.getDeclaredFields();
            for(Field f:fields){
                f.setAccessible(true);
                mistakedata += f.get(e).toString() + "---";
            }
            LogVO logVO = LogUtils.backlogVOWithError(mistakedata);
            LogUtils.writeLogToTxt(logVO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResultTool<String>(mistakedata);
    }








}
