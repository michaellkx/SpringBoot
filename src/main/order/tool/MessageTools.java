package main.order.tool;

import main.order.aop.log.LogUtils;
import main.order.aop.log.LogVO;
import main.order.tool.exception.MyException;

public class MessageTools {

    private static String phone = "17853592639";

    private static String message;

        public static void sendMessage(String message) throws MyException {
        sendMessage(message,phone);
    }

    public static void sendMessage(String message,String phone) throws MyException {
        message = message;
        phone = phone;
        sendMessage();
    }

    //动作    待认证 回家认证
    public static void sendMessage() throws MyException {
        //直接使用phone、message
        LogUtils.writeLogToTxt(new LogVO(),"C:\\Users\\admin\\Desktop\\定时任务test.txt");


    }

}
