package main.order.service;

import main.order.tool.Constant;
import main.order.tool.ResultTool;
import org.apache.commons.lang3.StringUtils;

public class UtilService {

    public static int handleStatus(ResultTool<String> resultTool){

        if(resultTool == null){
            return 20;  //失败
        }else{
            String resultCode = resultTool.getResultCode();
            if(StringUtils.isBlank(resultCode)
                    || Constant.FIAL_CODE.equals(resultCode)){
                return 20;  //失败
            }
        }
        return 30;  //成功

    }













}
