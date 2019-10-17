package com.atguigu.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @ClassName Queue_Produce
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/16 14:04
 **/

@Component
public class Queue_Produce {
    @Autowired
    private Queue queue;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(){
        jmsMessagingTemplate.convertAndSend(queue,"****: "+UUID.randomUUID().toString().substring(0,6));
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessageScheduled(){
        System.out.println("****定时发送消息..");
        sendMessage();
        System.out.println("****send success");
    }
}
