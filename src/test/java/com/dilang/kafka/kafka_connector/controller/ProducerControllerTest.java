package com.dilang.kafka.kafka_connector.controller;

import com.dilang.kafka.kafka_connector.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class ProducerControllerTest {

    @Mock
    private ProducerService producerService;

    private ProducerController producerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producerController = new ProducerController(producerService);
    }

    @Test
    void sendMessage_ShouldReturnSuccessMessage() {
        // Arrange
        String topic = "test-topic";
        String message = "test-message";
        doNothing().when(producerService).sendMessage(topic, message);

        // Act
        String result = producerController.sendMessage(topic, message);

        // Assert
        assertEquals("Message sent to topic " + topic, result);
        verify(producerService).sendMessage(topic, message);
    }
}
