package com.bcentral.test.message.consumer.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bcentral.test.message.shared.entities.Message;

@Configuration
public class MapConfig {

    @Bean
    public ConcurrentHashMap<Integer, Message> map() {
        return new ConcurrentHashMap<>();
    }
}
