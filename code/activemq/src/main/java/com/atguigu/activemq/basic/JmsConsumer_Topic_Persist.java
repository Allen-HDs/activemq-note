package com.atguigu.activemq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassName JmsConsumer_Topic_Persist
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/10 18:17
 **/
public class JmsConsumer_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://192.168.75.134:61616";
    public static final String TOPIC_NAME = " Topic_Persist";


    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("消费者:--->z5");
        //1.创建连接工厂,按照给定的URL地址,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂,获得连接 Connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z5");
        //3.创建会话Session
        //3.1 两个参数:①事务;②签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(具体是队列queue还是主题topic)
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark");
        connection.start();

        Message message = topicSubscriber.receive();
        while (null != message){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("****收到的持久化Persist Topic: "+textMessage.getText());
            message = topicSubscriber.receive();
        }
        session.close();
        connection.close();
        System.out.println("***********Persist Topic 消息接收完成*********");


        /**
         * 1.一定要先运行一次消费者,等于向MQ注册,类似我订阅了这个主题;
         * 2.然后再运行生产者发送消息,此时无论消费者是否在线,都会接收到;
         *   不在线的话,下次连接的时候,会把没有收到过的消息都接收下来
         */
    }
}
