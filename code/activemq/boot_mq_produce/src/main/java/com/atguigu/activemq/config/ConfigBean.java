package com.atguigu.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @ClassName ConfigBean
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/16 13:59
 **/
@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myqueue;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myqueue);
    }
}
