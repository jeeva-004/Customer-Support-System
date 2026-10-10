package com.customersupport.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketReplyRequest {
    private String message;
    private Long ticketId;
    private Long userId;
}
