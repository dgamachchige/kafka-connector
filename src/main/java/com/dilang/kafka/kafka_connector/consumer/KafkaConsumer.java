package com.dilang.kafka.kafka_connector.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "kafka-eventhub", groupId = "my-consumer-group")
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }
}

