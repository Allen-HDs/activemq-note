package com.atguigu.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName SpringMQ_Consumer
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/14 18:57
 **/
@Service
public class SpringMQ_Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer consumer = (SpringMQ_Consumer) ctx.getBean("springMQ_Consumer");//相当于new SpringMQ_Produce()
        String retValue = (String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println("****消费者接收到的消息:"+retValue);
    }
}
