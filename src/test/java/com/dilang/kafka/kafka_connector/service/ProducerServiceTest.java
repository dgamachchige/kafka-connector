package com.dilang.kafka.kafka_connector.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private ProducerService producerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producerService = new ProducerService(kafkaTemplate);
    }

    @Test
    void sendMessage_ShouldSendMessageToKafka() {
        // Arrange
        String topic = "test-topic";
        String message = "test-message";
        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(null);

        // Act
        producerService.sendMessage(topic, message);

        // Assert
        verify(kafkaTemplate).send(eq(topic), eq(message));
    }
}

