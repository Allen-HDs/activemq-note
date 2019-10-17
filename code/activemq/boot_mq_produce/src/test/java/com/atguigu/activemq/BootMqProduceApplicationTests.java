package com.atguigu.activemq;

import com.atguigu.activemq.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BootMqProduceApplicationTests {
	@Autowired
	private Queue_Produce queue_produce;

	@Test
	public void contextLoads() {
		queue_produce.sendMessage();
	}

}
