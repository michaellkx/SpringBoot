package main.order.aop.log;

import main.order.service.UtilService;
import main.order.mapper.LogMapper;
import main.order.tool.exception.MyException;
import main.order.tool.ResultTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//日志记录类
@Aspect
@Component(value = "GobalApiHandle")
public class handleLog {

   @Autowired
   private LogMapper logMapper;

   //记录前端传过来的Json数据
   @Before(value = "execution(public * main.order.controller.OrderController.*(..))")
   public void writeComeJson(JoinPoint joinPoint) throws MyException {

      try {
         if (joinPoint == null
                 || joinPoint.getTarget() == null
                 || joinPoint.getSignature() == null){
            throw new MyException("非法的joinPoint");
         }


         LogVO logVO = new LogVO();
         logVO.setDr(0);
         logVO.setCreaterUser("当前用户");
         logVO.setResponseString(null);
         logVO.setStatues(10);
         if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){  //滞留 通过反射返回方法的参数
            Object[] args = joinPoint.getArgs();
            String requestParam = "";
            for(Object o:args){//记录日志
               requestParam += o.toString();
               logVO.setRequestParam(requestParam);
            }
         }
         logMapper.writeLog(logVO); //写入数据库
         LogUtils.writeLogToTxt(logVO);   //写入txt文件

      } catch (Exception e) {
         throw new MyException("Aop写入日志"+e.getMessage());
      }

   }

   //记录处理后的Json数据
   @AfterReturning(value = "execution(public * main.order.controller.OrderController.*(..))",returning = "resulTool")
   public void updateLog(JoinPoint joinPoint, ResultTool<String> resulTool) throws MyException {

      try{

         if (resulTool == null){
            throw new MyException("非法的参数");
         }
         LogVO logVO = new LogVO("logpk_00001");
         logVO.setStatues(UtilService.handleStatus(resulTool));
         logVO.setResponseString(resulTool == null ? ""  : resulTool.toString());

         try {
            logVO.setStatues("200".equals(resulTool.getResultCode()) ? 30 : "400".equals(resulTool.getResultCode()) ? 20 : 10);
         }catch (NullPointerException e){
            logVO.setStatues(10);
         }

         logMapper.updateLog(logVO);   //动作

         LogUtils.writeLogToTxt(logVO);   //写入txt文件

      }catch (Exception e){
         throw new MyException("Aop更改日志失败"+e.getMessage());
      }
   }




}
