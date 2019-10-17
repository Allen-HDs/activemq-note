package com.atguigu.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @ClassName Topic_Produce
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/16 15:41
 **/
@Component
public class Topic_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void sendTopMessage() {
            jmsMessagingTemplate.convertAndSend(topic,"****主题消息"+ UUID.randomUUID().toString().substring(0,6));
    }
}
