package com.bcentral.test.message.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcentral.test.message.producer.dto.MessageResponseDto;
import com.bcentral.test.message.producer.dto.PostMessageRequestDto;
import com.bcentral.test.message.producer.dto.UpdateMessageRequestDto;
import com.bcentral.test.message.producer.service.MessageProducerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("message")
public class MessageProducerController {

    @Autowired
    private MessageProducerService service;

    @PostMapping
    public ResponseEntity<MessageResponseDto> insert(@RequestBody PostMessageRequestDto message) {
        MessageResponseDto response = service.insert(message);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDto> update(@PathVariable Integer id, @RequestBody UpdateMessageRequestDto message) {
        MessageResponseDto response = service.update(id, message);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDto> delete(@PathVariable Integer id) {
        MessageResponseDto response = service.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
