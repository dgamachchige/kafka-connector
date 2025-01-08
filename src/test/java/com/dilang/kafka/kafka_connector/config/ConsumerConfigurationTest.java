package com.dilang.kafka.kafka_connector.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsumerConfigurationTest {

    private ConsumerConfiguration consumerConfiguration;

    @BeforeEach
    void setUp() {
        consumerConfiguration = new ConsumerConfiguration();
        ReflectionTestUtils.setField(consumerConfiguration, "bootstrapServers", "test-server:9092");
        ReflectionTestUtils.setField(consumerConfiguration, "groupId", "test-group");
        ReflectionTestUtils.setField(consumerConfiguration, "securityProtocol", "SASL_SSL");
        ReflectionTestUtils.setField(consumerConfiguration, "saslMechanism", "PLAIN");
        ReflectionTestUtils.setField(consumerConfiguration, "jaasConfig", "test-config");
    }

    @Test
    void consumerFactory_ShouldCreateProperConfiguration() {
        // Act
        ConsumerFactory<String, String> factory = consumerConfiguration.consumerFactory();
        Map<String, Object> configs = ((org.springframework.kafka.core.DefaultKafkaConsumerFactory<String, String>) factory).getConfigurationProperties();

        // Assert
        assertEquals("test-server:9092", configs.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals("test-group", configs.get(ConsumerConfig.GROUP_ID_CONFIG));
        assertEquals(StringDeserializer.class, configs.get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG));
        assertEquals(StringDeserializer.class, configs.get(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG));
        assertEquals("SASL_SSL", configs.get("security.protocol"));
        assertEquals("PLAIN", configs.get("sasl.mechanism"));
        assertEquals("test-config", configs.get("sasl.jaas.config"));
    }

    @Test
    void kafkaListenerContainerFactory_ShouldCreateFactory() {
        // Act
        var factory = consumerConfiguration.kafkaListenerContainerFactory();

        // Assert
        assertNotNull(factory);
    }
}

