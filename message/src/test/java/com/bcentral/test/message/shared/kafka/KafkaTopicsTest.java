package com.bcentral.test.message.shared.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KafkaTopicsTest {
    @Test
    void testMessageTopicConstant() {
        String expectedTopic = "messagetopic-2";
        assertEquals(expectedTopic, KafkaTopics.MESSAGE_TOPIC);
    }
}
