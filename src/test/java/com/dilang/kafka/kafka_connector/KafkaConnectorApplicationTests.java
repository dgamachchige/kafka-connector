package com.dilang.kafka.kafka_connector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaConnectorApplicationTests {

	@BeforeAll
	static void setUpEnvironment() {
		System.setProperty("EVENTHUB_CONNECTION_STRING", "test-connection-string");
	}
	@Test
	void contextLoads() {
	}

}
