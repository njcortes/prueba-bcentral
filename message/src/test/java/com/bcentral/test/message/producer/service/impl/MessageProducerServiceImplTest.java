package com.bcentral.test.message.producer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import java.time.Instant;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import com.bcentral.test.message.producer.dto.MessageResponseDto;
import com.bcentral.test.message.producer.dto.PostMessageRequestDto;
import com.bcentral.test.message.producer.dto.UpdateMessageRequestDto;
import com.bcentral.test.message.shared.entities.Message;
import com.bcentral.test.message.shared.entities.Metadata;
import com.bcentral.test.message.shared.kafka.KafkaTopics;

public class MessageProducerServiceImplTest {

    @Mock
    private KafkaTemplate<Integer, Message> kafkaTemplate;

    @InjectMocks
    private MessageProducerServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSuccess() {

        PostMessageRequestDto request = new PostMessageRequestDto(1, Instant.now(), "Test Message",
                new Metadata("source", "notification"));
        Message mockMessage = Message.builder()
                .id(request.getId())
                .message(request.getMessage())
                .timestamp(request.getTimestamp())
                .metadata(request.getMetadata())
                .build();

        ProducerRecord<Integer, Message> producerRecord = new ProducerRecord<>(KafkaTopics.MESSAGE_TOPIC,
                mockMessage.getId(), mockMessage);

        MessageResponseDto response = service.insert(request);

        assertEquals(200, response.getCode());
        assertEquals("OK", response.getMessage());
        assertEquals("Mensaje Ingresado", response.getInternalMessage());
    }

    @Test
    void testInsertException() {
        PostMessageRequestDto request = new PostMessageRequestDto(1, Instant.now(), "Test Message",
                new Metadata("source", "notification"));

        // Simulate an exception in KafkaProducer
        doThrow(new RuntimeException("Kafka Error")).when(kafkaTemplate).send(any(ProducerRecord.class));

        MessageResponseDto response = service.insert(request);

        assertEquals(500, response.getCode());
        assertEquals("ERROR", response.getMessage());
        assertEquals("NO OK", response.getInternalMessage());
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        UpdateMessageRequestDto request = new UpdateMessageRequestDto(1, Instant.now(), "Test Message",
                new Metadata("source", "notification"));

        MessageResponseDto response = service.update(id, request);

        assertEquals(204, response.getCode());
        assertEquals("OK", response.getMessage());
        assertEquals("To be implemented...", response.getInternalMessage());
    }

    @Test
    void testDelete() {
        Integer id = 1;

        MessageResponseDto response = service.delete(id);

        assertEquals(204, response.getCode());
        assertEquals("OK", response.getMessage());
        assertEquals("To be implemented...", response.getInternalMessage());
    }
}
