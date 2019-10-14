package com.atguigu.activemq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName JmsProducer
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/10 18:17
 **/
public class JmsProducer_TS {
    public static final String ACTIVEMQ_URL="tcp://192.168.75.134:61616";
    public static final String QUEUE_NAME=" ts_01";


    public static void main(String[] args) throws JMSException {

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
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //6.通过使用消息生产者messageProducer 生产3条消息发送到MQ队列里面
        for (int i = 0; i < 3; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("textMessage" + i);
//            textMessage.setStringProperty("k01","vip");
            //8.通过 messageProducer 发送消息给 MQ
            messageProducer.send(textMessage);

           /* MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("k1","mapMessage" + i);
            messageProducer.send(mapMessage);*/
        }
        //9.关闭资源
        messageProducer.close();
        //带事务提交:在session关闭前需要 commit(消费者也需要此)
//        session.commit();
        session.close();
        connection.close();
        System.out.println("***********消息发布到MQ完成*********");

        /*try {
            //一切ok
            session.commit();
        }catch (Exception e){
            //出了异常  回滚
            session.rollback();
        }finally {
            if (null != session){
                session.close();
            }
        }*/
    }
}
