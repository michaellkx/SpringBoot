package main.order.aop.log;

import lombok.Data;
import main.order.annotation.comment.Comment;
import main.order.entity.baseVO.BaseVO;

import java.util.Date;
import java.util.UUID;

@Data
public class LogVO extends BaseVO {

    @Comment("日志标识")
    private String logPk;

    @Comment("请求参数")
    private String requestParam;

    @Comment("请求时间")
    private Date requestDate;

    @Comment("请求状态")
    private int statues;    //10:未处理   20:处理失败   30:处理成功

    @Comment("异常信息")
    private String exceptionMessage;   //状态为20时,才会有异常信息

    @Comment("返回值")
    private String responseString;

    public LogVO(){
        this.requestDate = new Date();
        this.logPk = "pk_"+UUID.randomUUID().toString()
                .replaceAll("\r","")
                .replace("\n","")
                .replace("-","")
                .substring(0,14)
                .toUpperCase();
    }

    public LogVO(String logPk){
        this.logPk = logPk;
    }



}
