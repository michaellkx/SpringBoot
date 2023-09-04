package main.order.sys;

import lombok.Data;

@Data
public class GloabPoxyInstance {
    private static GloabPoxyInstance instance = new GloabPoxyInstance();

    private GloabPoxyInstance(){
    }

    public static GloabPoxyInstance getInstance(){
        if(instance == null){
            instance = new GloabPoxyInstance();
        }
        return instance;
    }


}
