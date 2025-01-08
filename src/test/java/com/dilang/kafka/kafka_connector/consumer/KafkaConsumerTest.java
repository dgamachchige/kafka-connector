package com.dilang.kafka.kafka_connector.consumer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
class KafkaConsumerTest {

    private KafkaConsumer kafkaConsumer;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        kafkaConsumer = new KafkaConsumer();

        // Set up logger capturing
        Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    void listen_ShouldLogReceivedMessage() {
        // Arrange
        String message = "test-message";

        // Act
        kafkaConsumer.listen(message);

        // Assert
        ILoggingEvent loggingEvent = listAppender.list.get(0);
        assertEquals(Level.INFO, loggingEvent.getLevel());
        assertEquals("Received message: " + message, loggingEvent.getFormattedMessage());
    }
}
