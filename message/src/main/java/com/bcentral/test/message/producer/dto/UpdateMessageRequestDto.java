package com.bcentral.test.message.producer.dto;

import java.io.Serializable;
import java.time.Instant;

import com.bcentral.test.message.shared.entities.Metadata;

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
public class UpdateMessageRequestDto implements Serializable {
    private Integer id;
    private Instant updated;
    private String message;
    private Metadata metadata;
}
