package com.atguigu.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassName MyMessageListener
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/15 12:33
 **/
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (null != message && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("****监听消费:"+textMessage.getText()+"****");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
