package com.bcentral.test.message.consumer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcentral.test.message.consumer.dto.GetMessageAllResponseDto;
import com.bcentral.test.message.consumer.dto.GetMessageIdResponseDto;
import com.bcentral.test.message.consumer.kafka.MessageStorage;
import com.bcentral.test.message.consumer.service.MessageConsumerService;

@Service
public class MessageConsumerServiceImpl implements MessageConsumerService {

    @Autowired
    MessageStorage messageStorage;

    @Override
    public GetMessageAllResponseDto getAllMessages() {
        GetMessageAllResponseDto response;

        try {
            response = new GetMessageAllResponseDto(200, messageStorage.findAll());
        } catch (Exception e) {
            response = new GetMessageAllResponseDto(500, List.of());
        }
        return response;
    }

    @Override
    public GetMessageIdResponseDto getMessage(Integer id) {
        GetMessageIdResponseDto response;

        try {
            response = new GetMessageIdResponseDto(200, messageStorage.findById(id));
        } catch (Exception e) {
            response = new GetMessageIdResponseDto(500, null);
        }
        return response;
    }
}
