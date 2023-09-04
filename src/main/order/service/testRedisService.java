//package main.order.service;
//
//
//import main.order.tool.CacheTool;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.Jedis;
//
//import java.util.List;
//
//@RestController
//public class testRedisService {
//
//    private Jedis singleJedis = CacheTool.getInstance().getJedis();
//
//    private Jedis masterJedis = DoubleCacheTool.getInstance().getMasterJedis();
//
//    private Jedis slaverJedis = DoubleCacheTool.getInstance().getSlaverJedis();
//
//
//    @RequestMapping("/testSingleRedis")
//    public void testSingleRedis(){
//
//        singleJedis.lpush("sssss","值值值值值值值值值值");
//
//    }
//
//    //测试redis主从复制    读写分离
//    @RequestMapping("/testDoubleRedis")
//    public String testDoubleRedis(String testString){
//
//        masterJedis.lpush("303",testString);
//
//        List<String> lrange = slaverJedis.lrange("303", 0, -1);
//
//        //List<String> blpop = slaverJedis.blpop(new String[]{"303"});
//
//        System.out.println(lrange.toString());
//
//        return lrange.toString();
//    }
//
//
//
//
//
//}
