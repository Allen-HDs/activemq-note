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
public class JmsConsumer {
    //        public static final String ACTIVEMQ_URL = "tcp://192.168.75.134:61616";
    public static final String ACTIVEMQ_URL = "nio://192.168.75.134:61618";
    public static final String QUEUE_NAME = " queue01";


    public static void main(String[] args) throws JMSException, IOException {

        //1.创建连接工厂,按照给定的URL地址,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂,获得连接 Connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话Session
        //3.1 两个参数:①事务;②签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(具体是队列queue还是主题topic)
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //6.messageConsumer 消费MQ的消息
        //接收方式一:
        /*
         *同步阻塞方式(receive())
         * 订阅者或接收者调用MessageConsumer的receive()方法来接收消息,
         * receive()方法在能够接收到消息之前(或超时之前)将一直阻塞
         *
        while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive(6000L);
            if (null != textMessage){
                System.out.println("*****消费者接收到消息: "+textMessage.getText());
            }else {
                break;
        }
        }*/

        //方法二:通过监听的方式来消费消息
        //异步非阻塞方式(监听器onMessage())
        //订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener messageListener)注册一个消息监听器
        //当消息到达之后,系统自动调用监听器MessageListener 的 onMessage(Message message)方法
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("*****消费者接收到消息: " + textMessage.getText());
//                        System.out.println("*****消费者接收到消息属性: " + textMessage.getStringProperty("k1"));

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

                /*if (message != null && message instanceof MapMessage) {
                    MapMessage mapMessage = (MapMessage) message;
                    try {
                        System.out.println("*****消费者接收到消息: " + mapMessage.getString("k1"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }*/
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
