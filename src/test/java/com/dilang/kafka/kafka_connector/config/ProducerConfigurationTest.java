package com.dilang.kafka.kafka_connector.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProducerConfigurationTest {

    private ProducerConfiguration producerConfiguration;

    @BeforeEach
    void setUp() {
        producerConfiguration = new ProducerConfiguration();
        ReflectionTestUtils.setField(producerConfiguration, "bootstrapServers", "test-server:9092");
        ReflectionTestUtils.setField(producerConfiguration, "securityProtocol", "SASL_SSL");
        ReflectionTestUtils.setField(producerConfiguration, "saslMechanism", "PLAIN");
        ReflectionTestUtils.setField(producerConfiguration, "jaasConfig", "test-config");
    }

    @Test
    void producerFactory_ShouldCreateProperConfiguration() {
        // Act
        ProducerFactory<String, String> factory = producerConfiguration.producerFactory();
        Map<String, Object> configs = ((org.springframework.kafka.core.DefaultKafkaProducerFactory<String, String>) factory).getConfigurationProperties();

        // Assert
        assertEquals("test-server:9092", configs.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringSerializer.class, configs.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals(StringSerializer.class, configs.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
        assertEquals("SASL_SSL", configs.get("security.protocol"));
        assertEquals("PLAIN", configs.get("sasl.mechanism"));
        assertEquals("test-config", configs.get("sasl.jaas.config"));
    }

    @Test
    void kafkaTemplate_ShouldCreateTemplate() {
        // Act
        var template = producerConfiguration.kafkaTemplate();

        // Assert
        assertNotNull(template);
    }
}
