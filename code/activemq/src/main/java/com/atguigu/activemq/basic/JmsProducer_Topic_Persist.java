package com.atguigu.activemq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName JmsProducer_Topic
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/10 18:17
 **/
public class JmsProducer_Topic_Persist {
    public static final String ACTIVEMQ_URL="tcp://192.168.75.134:61616";
    public static final String TOPIC_NAME=" Topic_Persist";


    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂,按照给定的URL地址,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂,获得连接 Connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();

        //3.创建会话Session
        //3.1 两个参数:①事务;②签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(具体是队列queue还是主题topic)
        Topic topic = session.createTopic(TOPIC_NAME);
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        //6.通过使用消息生产者messageProducer 生产3条消息发送到MQ队列里面
        for (int i = 0; i < 3; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("msg-persist-" + i);
            //8.通过 messageProducer 发送消息给 MQ
            messageProducer.send(textMessage);
        }
        //9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*********** Persist Topic 消息发布到MQ完成*********");
    }
}
