package com.bcentral.test.message.consumer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bcentral.test.message.consumer.dto.GetMessageAllResponseDto;
import com.bcentral.test.message.consumer.dto.GetMessageIdResponseDto;
import com.bcentral.test.message.consumer.service.MessageConsumerService;

public class MessageConsumerControllerTest {
     @InjectMocks
    private MessageConsumerController controller;

    @Mock
    private MessageConsumerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessages() {
        GetMessageAllResponseDto responseDto = new GetMessageAllResponseDto();
        responseDto.setCode(HttpStatus.OK.value());

        when(service.getAllMessages()).thenReturn(responseDto);

        ResponseEntity<GetMessageAllResponseDto> response = controller.getAllMessages();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(service, times(1)).getAllMessages();
    }

    @Test
    void testGetMessage() {
        Integer id = 1;
        GetMessageIdResponseDto responseDto = new GetMessageIdResponseDto();
        responseDto.setCode(HttpStatus.OK.value());

        when(service.getMessage(id)).thenReturn(responseDto);

        ResponseEntity<GetMessageIdResponseDto> response = controller.getMessage(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(service, times(1)).getMessage(id);
    }
}
