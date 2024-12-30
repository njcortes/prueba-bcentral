package com.bcentral.test.message.consumer.dto;

import java.util.List;

import com.bcentral.test.message.shared.entities.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetMessageAllResponseDto {
    private int code;
    private List<Message> messages;
}
