package main.order.entity.baseVO;

import lombok.Data;
import main.order.annotation.autofill.DefAutoFillValue;

import java.util.Date;


@DefAutoFillValue  //自定义注解以实现把为null的值赋值成"~"
@Data
public class BaseVO {

    private boolean ifnew = false;

    private Date ts;    //时间戳  每次对此VO进行数据库操作就会修改此戳

    private String createrUser; //创建用户

    private int Dr; //删除标志

    private Date createTime;    //此VO的创建时间

    public BaseVO(){
        this.ts = new Date();
        this.Dr = 0;
    }

    public BaseVO(String createrUser){
        this();
        this.createrUser = createrUser;
        this.createTime = new Date();
    }




}
