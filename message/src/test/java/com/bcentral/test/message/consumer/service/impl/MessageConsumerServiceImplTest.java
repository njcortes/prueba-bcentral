package com.bcentral.test.message.consumer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bcentral.test.message.consumer.dto.GetMessageAllResponseDto;
import com.bcentral.test.message.consumer.dto.GetMessageIdResponseDto;
import com.bcentral.test.message.consumer.kafka.MessageStorage;
import com.bcentral.test.message.shared.entities.Message;

public class MessageConsumerServiceImplTest {
    @Mock
    private MessageStorage messageStorage;

    @InjectMocks
    private MessageConsumerServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessagesSuccess() {
        Message message1 = new Message();
        message1.setId(1);
        message1.setMessage("Message 1");

        Message message2 = new Message();
        message2.setId(2);
        message2.setMessage("Message 2");

        List<Message> mockMessages = List.of(message1, message2);
        when(messageStorage.findAll()).thenReturn(mockMessages);

        GetMessageAllResponseDto response = service.getAllMessages();

        assertEquals(200, response.getCode());
        assertEquals(mockMessages, response.getMessages());
    }

    @Test
    void testGetAllMessagesException() {

        when(messageStorage.findAll()).thenThrow(new RuntimeException("Error"));

        GetMessageAllResponseDto response = service.getAllMessages();

        assertEquals(500, response.getCode());
        assertTrue(response.getMessages().isEmpty());
    }

    @Test
    void testGetMessageSuccess() {
        Integer messageId = 1;

        Message message1 = new Message();
        message1.setId(messageId);
        message1.setMessage("Message 1");

        when(messageStorage.findById(messageId)).thenReturn(message1);

        GetMessageIdResponseDto response = service.getMessage(messageId);
        
        assertEquals(200, response.getCode());
        assertEquals(message1, response.getMessage());
    }

    @Test
    void testGetMessageException() {
        
        Integer messageId = 1;
        when(messageStorage.findById(messageId)).thenThrow(new RuntimeException("Error"));

        GetMessageIdResponseDto response = service.getMessage(messageId);

        assertEquals(500, response.getCode());
        assertNull(response.getMessage());
    }
}
