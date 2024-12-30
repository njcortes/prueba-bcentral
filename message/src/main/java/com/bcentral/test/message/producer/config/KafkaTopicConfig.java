package com.bcentral.test.message.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.bcentral.test.message.shared.kafka.KafkaTopics;

@Configuration
public class KafkaTopicConfig {

    public KafkaTopicConfig() {
    }

    @Bean
    public NewTopic messagetopic() {
        return TopicBuilder.name(KafkaTopics.MESSAGE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
