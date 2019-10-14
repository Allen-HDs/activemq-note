package com.atguigu.activemq.embed;
import org.apache.activemq.broker.BrokerService;

/**
 * @ClassName EmbedBroker
 * @Description TODO
 * @Author yuxiang
 * @Date 2019/10/14 17:37
 **/
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        //ActiviteMQ 也支持在vm中通信 基于嵌入式的 broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }

}
