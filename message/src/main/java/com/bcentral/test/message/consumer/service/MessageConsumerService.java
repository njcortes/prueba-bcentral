package com.bcentral.test.message.consumer.service;

import com.bcentral.test.message.consumer.dto.GetMessageAllResponseDto;
import com.bcentral.test.message.consumer.dto.GetMessageIdResponseDto;

public interface MessageConsumerService {

    public GetMessageAllResponseDto getAllMessages();
    public GetMessageIdResponseDto getMessage(Integer id);
    
}
