package main.order.tool;

import lombok.Data;
import redis.clients.jedis.Jedis;

//测试redis集群
@Data
public class DoubleCacheTool {

    private static DoubleCacheTool instance;

    public static DoubleCacheTool getInstance(){

        if(instance == null){
            instance = new DoubleCacheTool();
        }
        return instance;

    }

    private Jedis masterJedis = new Jedis("127.0.0.1", 1001);
    private Jedis slaverJedis = new Jedis("127.0.0.1", 1002);

    private DoubleCacheTool(){
        super();
        this.init();
    }

    private void init(){
        masterJedis.auth("def123456");
        slaverJedis.auth("def123456");
    }

    public void colse(){
        masterJedis.close();
        slaverJedis.close();
    }

}
