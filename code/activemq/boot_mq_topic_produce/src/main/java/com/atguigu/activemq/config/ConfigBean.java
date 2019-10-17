package com.atguigu.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @ClassName ConfigBean
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/16 15:39
 **/
@Component
public class ConfigBean {

    @Value("${myTopic}")
    private String topicName;

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }
}
