package main.order.entity;

import lombok.Data;
import main.order.entity.baseVO.BaseVO;

import java.lang.reflect.Field;

@Data
public class OrderVO extends BaseVO {

    private String orderPk;

    private String orderId;

    private String orderName;

    private int orderNum;

    public OrderVO(){
        super();
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        try {
            Class<?> aClass = Class.forName("main.order.entity.OrderVO");
            Field[] declaredFields = aClass.getDeclaredFields();
            for(Field f:declaredFields){
                f.setAccessible(true);
                sb.append(f.getName());
                sb.append(":");
                sb.append(f.get(this));
                sb.append("\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sb.toString();
    }

}
