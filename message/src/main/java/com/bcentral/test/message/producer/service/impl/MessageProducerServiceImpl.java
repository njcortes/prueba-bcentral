package com.bcentral.test.message.producer.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bcentral.test.message.producer.dto.MessageResponseDto;
import com.bcentral.test.message.producer.dto.PostMessageRequestDto;
import com.bcentral.test.message.producer.dto.UpdateMessageRequestDto;
import com.bcentral.test.message.producer.service.MessageProducerService;
import com.bcentral.test.message.shared.entities.Message;
import com.bcentral.test.message.shared.kafka.KafkaTopics;

@Service
public class MessageProducerServiceImpl implements MessageProducerService {

    private final KafkaTemplate<Integer, Message> kafkaTemplate;

    public MessageProducerServiceImpl(KafkaTemplate<Integer, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public MessageResponseDto insert(PostMessageRequestDto message) {
        MessageResponseDto response;

        try {
            Message objMessage = Message.builder().id(message.getId()).message(message.getMessage())
                    .timestamp(message.getTimestamp()).metadata(message.getMetadata()).build();
            ProducerRecord producer = new ProducerRecord<>(KafkaTopics.MESSAGE_TOPIC, objMessage.getId(), objMessage);
            kafkaTemplate.send(producer);

            response = new MessageResponseDto(200, "OK", "Mensaje Ingresado");

        } catch (Exception e) {
            response = new MessageResponseDto(500, "ERROR", "NO OK");

        }

        return response;
    }

    @Override
    public MessageResponseDto update(Integer id, UpdateMessageRequestDto message) {

        MessageResponseDto response = new MessageResponseDto();

        // TODO: Implements code for update

        response.setCode(204);
        response.setMessage("OK");
        response.setInternalMessage("To be implemented...");

        return response;
    }

    @Override
    public MessageResponseDto delete(Integer id) {

        MessageResponseDto response = new MessageResponseDto();

        // TODO: Implements code for delete

        response.setCode(204);
        response.setMessage("OK");
        response.setInternalMessage("To be implemented...");

        return response;
    }

}
