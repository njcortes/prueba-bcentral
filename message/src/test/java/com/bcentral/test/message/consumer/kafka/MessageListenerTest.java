package com.bcentral.test.message.consumer.kafka;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bcentral.test.message.shared.entities.Message;

public class MessageListenerTest {
    @InjectMocks
    private MessageListener listener;

    @Mock
    private MessageStorage messageStorage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListener() {
        Message message = new Message();
        message.setMessage("Test Message");

        listener.listener(message);

        verify(messageStorage, times(1)).put(message);
    }
}
