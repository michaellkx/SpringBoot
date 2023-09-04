package main.order.tool;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class ResultTool<T> {

    private String resultCode;  //200为成功     400为失败
    private T t;    //携带的数据

    public ResultTool(){
        super();
        this.fail();
    }

    public ResultTool (T t){
        super();
        this.resultCode = Constant.SUCCESS_CODE;
        this.t = t;
    }

//    public ResultTool<T> success(T t){
//        this.resultCode = Constant.SUCCESS_CODE;
//        this.t = t;
//        return null;
//    }

    public void fail(){
        this.resultCode = Constant.FIAL_CODE;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("返回泛型为:");
        try{
            Class<ResultTool> resultToolClass = ResultTool.class;
            Field[] declaredFields = resultToolClass.getDeclaredFields();

            for(Field f:declaredFields){
                f.setAccessible(true);
                Object o = f.get(this);
                sb.append(o.toString()+ "\n\t");
            }

        }catch (Exception e){
            //throw new MyException(e.getMessage());
        }
        return sb.toString();

    }


}
