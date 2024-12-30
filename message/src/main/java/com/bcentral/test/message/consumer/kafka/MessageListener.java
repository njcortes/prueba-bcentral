package com.bcentral.test.message.consumer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bcentral.test.message.shared.entities.Message;
import com.bcentral.test.message.shared.kafka.KafkaTopics;

@Component
public class MessageListener {

    @Autowired
    MessageStorage messageStorage;

    @KafkaListener(topics = KafkaTopics.MESSAGE_TOPIC)
    public void listener(@Payload Message message) {
        messageStorage.put(message);
    }
}
