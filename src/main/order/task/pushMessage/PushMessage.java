package main.order.task.pushMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import java.util.Date;

//使用原生线程实现定时任务
@Component
public class PushMessage implements SchedulingConfigurer {

    @Value("${pushMessageTime}")    //在yml中配置了每天的推送时间
    private String pushTime;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        PushMessageThread pushMessageThread = new PushMessageThread();  //自定义一个线程

        scheduledTaskRegistrar.addCronTask(pushMessageThread,"0/3 * * * * ? "); //把线程丢入多线程注册器中

    }

    class PushMessageThread extends Thread{
        private volatile boolean flag = true;

        @Override
        public void run() {
            while(flag){
                this.doAction();
            }
        }

        private void doAction(){
            String todayTime = new Date().toString().substring(11, 19);
            if(Integer.parseInt(todayTime.substring(0,2)) > Integer.parseInt(pushTime.substring(0,2))){  //如果此时的小时数大于yml定义的小时数
                System.out.println(new Date().toString() + "推送短信");
                //在此进行短息的推送         TODO  TODO  TODO


                flag = false;   //结束线程
            }

        }
    }


}



