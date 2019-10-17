package com.atguigu.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @ClassName Topic_Consumer
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/16 15:20
 **/
@Component
public class Topic_Consumer {

    @JmsListener(destination= "${myTopic}")
    public void receiveMessage(Message message) throws JMSException{
        if (message != null && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("**Topic消费到消息: "+textMessage.getText());
        }
    }

}
