package com.bcentral.test.message.consumer.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bcentral.test.message.shared.entities.Message;

public class MessageStorageTest {
    private MessageStorage storage;

    @BeforeEach
    void setUp() {
        storage = new MessageStorage();
    }

    @Test
    void testPut() {
        Message message = new Message();
        message.setId(1);
        message.setMessage("Test Message");

        storage.put(message);
        assertEquals(message, storage.findById(1));
    }

    @Test
    void testFindById() {
        Message message1 = new Message();
        message1.setId(1);
        message1.setMessage("Message 1");

        Message message2 = new Message();
        message2.setId(2);
        message2.setMessage("Message 2");

        storage.put(message1);
        storage.put(message2);

        Message result = storage.findById(2);
        assertNotNull(result);
        assertEquals("Message 2", result.getMessage());
    }

    @Test
    void testFindAll() {
        Message message1 = new Message();
        message1.setId(1);
        message1.setMessage("Message 1");

        Message message2 = new Message();
        message2.setId(2);
        message2.setMessage("Message 2");

        storage.put(message1);
        storage.put(message2);

        List<Message> result = storage.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(message1));
        assertTrue(result.contains(message2));
    }

    @Test
    void testClear() {
        Message message = new Message();
        message.setId(1);
        message.setMessage("Test Message");

        storage.put(message);
        assertEquals(1, storage.findAll().size());

        storage.clear();
        assertTrue(storage.findAll().isEmpty());
    }
}
