package main.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement    //Springboot默认开启
@MapperScan(basePackages = "main.order.mapper")
@ComponentScan(basePackages = "main")
@EnableScheduling //开启定时任务
public class App {

    public static void main(String[] args) {

//        CacheTool.getInstance();//开启缓存
//
//        DoubleCacheTool.getInstance();//开启主从缓存

     //   new PushMessageTread().//使用原生多线程来实现定时任务


        SpringApplication.run(App.class,args);

    }



}
