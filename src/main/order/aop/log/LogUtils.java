package main.order.aop.log;

import main.order.annotation.comment.Comment;
import main.order.tool.exception.MyException;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

public class LogUtils {

    private static String documentPath = "";

    public static void writeLogToTxt(LogVO logVO,String documentPath) throws MyException {

        documentPath = documentPath;
        writeLogToTxt(logVO);

    }
     public static void writeLogToTxt(LogVO logVO) throws MyException{

        try{
            String path ;
            if(StringUtils.isBlank(documentPath)){
                path = "C:\\Users\\admin\\Desktop\\StudyJava\\SpringClould\\Log.txt";   //默认值
            }else{
                path = documentPath;
            }
            FileWriter txt =  new FileWriter(path,true);
            StringBuilder item = new StringBuilder("");
            Class<?> aClass = LogVO.class;
            Field[] declaredFields = aClass.getDeclaredFields();
            for(Field f:declaredFields){
                f.setAccessible(true);
                if(f.getAnnotation(Comment.class) != null){
                    item.append(f.getAnnotation(Comment.class).value()+":");
                }
                Object o = f.get(logVO);
                String value = o == null ? "null" : o.toString().equals("") ? "null" : o.toString();
                item.append(value);
                item.append("\n");
            }
            txt.flush();
            txt.append(item.toString());
            txt.append("****************************************************************\n");
            txt.close();
        }catch (IOException e){
            throw new MyException("日志写入文件路径出错"+e.getMessage());
        }catch (java.lang.IllegalAccessException e){
            throw new MyException("日志写入文件反射操作出错"+e.getMessage());
        }
    }

    //返回一个包含错误信息的日志VO
    public static LogVO backlogVOWithError(String exceptionMessage){
        LogVO logVO = new LogVO();
        logVO.setStatues(20);
        logVO.setExceptionMessage(exceptionMessage);
        return logVO;
    }

}
