package com.atguigu.activemq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/10 18:17
 **/
public class JmsConsumer_Topic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.75.134:61616";
    public static final String TOPIC_NAME=" topic_atguigu";


    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是3号消费者");

        //1.创建连接工厂,按照给定的URL地址,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂,获得连接 Connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话Session
        //3.1 两个参数:①事务;②签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(具体是队列queue还是主题topic)
        Topic topic = session.createTopic(TOPIC_NAME);

        //5.创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //6.messageConsumer 消费MQ的消息
        messageConsumer.setMessageListener((message) ->{
            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("*****消费者接收到topic消息: " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //7.关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("***********消息接收完成*********");
    }
}
