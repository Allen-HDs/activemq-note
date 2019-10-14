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
public class JmsConsumer_TS {
    public static final String ACTIVEMQ_URL = "tcp://192.168.75.134:61616";
    public static final String QUEUE_NAME = " ts_01";


    public static void main(String[] args) throws JMSException, IOException {

        //1.创建连接工厂,按照给定的URL地址,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂,获得连接 Connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话Session
        //3.1 两个参数:①事务;②签收
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //4.创建目的地(具体是队列queue还是主题topic)
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //6.messageConsumer 消费MQ的消息
        /*
         *同步阻塞方式(receive())
         * 订阅者或接收者调用MessageConsumer的receive()方法来接收消息,
         * receive()方法在能够接收到消息之前(或超时之前)将一直阻塞
         **/
        while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);
            if (null != textMessage) {
                System.out.println("*****消费者接收到消息: " + textMessage.getText());
                //当签收方式设置为手动(CLIENT_ACKNOWLEDGE)时,需要加上这行代码 message.acknowledge()手动签收
                //但是当带事务提交时,connect.createSession的第二个参数就会变成自动签收
                textMessage.acknowledge();
            } else {
                break;
            }
        }

        //7.关闭资源
        messageConsumer.close();
        //带事务提交(connection.createSession(true)):在session关闭前需要 commit(生产者者也需要此)
//        session.commit();
        session.close();
        connection.close();
        System.out.println("***********消息接收完成*********");
    }
}
