package com.bcentral.test.message.shared.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bcentral.test.message.shared.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomSerializerTest {
    private CustomSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new CustomSerializer();
    }

    @Test
    void testSerializeValidMessage() throws JsonProcessingException {
        Message message = new Message();
        message.setMessage("Test message");

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] expectedBytes = objectMapper.writeValueAsBytes(message);

        byte[] result = serializer.serialize("test-topic", message);

        assertNotNull(result);
        assertArrayEquals(expectedBytes, result);
    }

    @Test
    void testSerializeNullMessage() {
        byte[] result = serializer.serialize("test-topic", null);

        assertNull(result);
    }

    @Test
    void testSerializeThrowsSerializationException() {
        Message message = mock(Message.class);
        when(message.getMessage()).thenThrow(new RuntimeException("Mocked Exception"));

        assertThrows(SerializationException.class, () -> {
            serializer.serialize("test-topic", message);
        });
    }
}
