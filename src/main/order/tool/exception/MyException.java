package main.order.tool.exception;

import main.order.aop.log.LogUtils;
import main.order.aop.log.LogVO;

public class MyException extends Exception {

    public MyException() throws Exception {
        super();
    }

    public MyException(String str){
        super(str);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }


}
