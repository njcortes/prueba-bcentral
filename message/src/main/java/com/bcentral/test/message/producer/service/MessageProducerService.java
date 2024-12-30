package com.bcentral.test.message.producer.service;

import com.bcentral.test.message.producer.dto.MessageResponseDto;
import com.bcentral.test.message.producer.dto.PostMessageRequestDto;
import com.bcentral.test.message.producer.dto.UpdateMessageRequestDto;

public interface MessageProducerService {

    public MessageResponseDto insert(PostMessageRequestDto message);
    public MessageResponseDto update(Integer id, UpdateMessageRequestDto message);
    public MessageResponseDto delete(Integer id);
}
