package com.bcentral.test.message.shared.entities;

import java.time.Instant;

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
public class Message {
    private Integer id;
    private Instant timestamp;
    private Instant updated;
    private String message;
    private Metadata metadata;
}
