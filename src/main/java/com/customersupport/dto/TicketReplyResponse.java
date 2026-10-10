package com.customersupport.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketReplyResponse {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long ticketId;
    private Long userId;

}
