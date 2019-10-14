package com.atguigu.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @ClassName SpringMQ_Produce
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/14 18:07
 **/
@Service
public class SpringMQ_Produce {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Produce produce = (SpringMQ_Produce) ctx.getBean("springMQ_Produce");//相当于new SpringMQ_Produce()
        produce.jmsTemplate.send((session) ->{
            TextMessage textMessage = session.createTextMessage("****Spring和ActiveMQ的整合Case..");
            return textMessage;
        });
        System.out.println("****Send Task Is Over....****");

    }
}
