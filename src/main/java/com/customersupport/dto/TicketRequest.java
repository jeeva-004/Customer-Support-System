package com.customersupport.dto;

import lombok.*;

@Getter
@Setter
public class TicketRequest {
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long customerId;
    private Long assignedAgentId;
    private Long categoryId;
}