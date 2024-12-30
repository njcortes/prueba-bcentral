package com.bcentral.test.message.consumer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bcentral.test.message.consumer.dto.GetMessageAllResponseDto;
import com.bcentral.test.message.consumer.dto.GetMessageIdResponseDto;
import com.bcentral.test.message.consumer.service.MessageConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("message")
public class MessageConsumerController {

    @Autowired
    private MessageConsumerService service;

    @GetMapping("/")
    public ResponseEntity<GetMessageAllResponseDto> getAllMessages() {
        GetMessageAllResponseDto response = service.getAllMessages();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetMessageIdResponseDto> getMessage(@PathVariable Integer id) {
        GetMessageIdResponseDto response = service.getMessage(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
