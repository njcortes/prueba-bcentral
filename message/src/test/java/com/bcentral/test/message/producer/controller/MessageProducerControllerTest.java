package com.bcentral.test.message.producer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bcentral.test.message.producer.dto.MessageResponseDto;
import com.bcentral.test.message.producer.dto.PostMessageRequestDto;
import com.bcentral.test.message.producer.dto.UpdateMessageRequestDto;
import com.bcentral.test.message.producer.service.MessageProducerService;

@ExtendWith(MockitoExtension.class)
public class MessageProducerControllerTest {
    @InjectMocks
    private MessageProducerController controller;

    @Mock
    private MessageProducerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        PostMessageRequestDto requestDto = new PostMessageRequestDto();
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setCode(HttpStatus.CREATED.value());

        when(service.insert(requestDto)).thenReturn(responseDto);

        ResponseEntity<MessageResponseDto> response = controller.insert(requestDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(service, times(1)).insert(requestDto);
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        UpdateMessageRequestDto requestDto = new UpdateMessageRequestDto();
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setCode(HttpStatus.OK.value());

        when(service.update(id, requestDto)).thenReturn(responseDto);

        ResponseEntity<MessageResponseDto> response = controller.update(id, requestDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(service, times(1)).update(id, requestDto);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setCode(HttpStatus.NO_CONTENT.value());

        when(service.delete(id)).thenReturn(responseDto);

        ResponseEntity<MessageResponseDto> response = controller.delete(id);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(service, times(1)).delete(id);
    }
}
