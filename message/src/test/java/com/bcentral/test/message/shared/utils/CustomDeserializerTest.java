package com.bcentral.test.message.shared.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bcentral.test.message.shared.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDeserializerTest {
    private CustomDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new CustomDeserializer();
    }

    @Test
    void testDeserializeValidData() throws JsonProcessingException {
        Message expectedMessage = new Message();
        expectedMessage.setMessage("Test content");

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = objectMapper.writeValueAsString(expectedMessage).getBytes(StandardCharsets.UTF_8);

        Message result = deserializer.deserialize("test-topic", data);

        assertNotNull(result);
        assertEquals(expectedMessage.getMessage(), result.getMessage());
    }

    @Test
    void testDeserializeNullData() {
        Message result = deserializer.deserialize("test-topic", null);

        assertNull(result);
    }

    @Test
    void testDeserializeThrowsSerializationException() {
        byte[] invalidData = "Invalid Message".getBytes(StandardCharsets.UTF_8);

        assertThrows(SerializationException.class, () -> {
            deserializer.deserialize("test-topic", invalidData);
        });
    }
}
