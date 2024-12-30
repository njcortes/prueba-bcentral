package com.bcentral.test.message.consumer.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bcentral.test.message.shared.entities.Message;

@Component
public class MessageStorage {
    private List<Message> storage = new ArrayList<>();

    public void put(Message message) {
        storage.add(message);
    }

    public Message findById(Integer id) {
        Message result = storage.stream()
                .filter(item -> item.getId().equals(id)).findFirst().get();
        return result;
    }

    public List<Message> findAll() {
        List<Message> result = storage.stream()
                .collect(Collectors.toList());
        return result;
    }

    public void clear() {
        storage.clear();
    }
}
