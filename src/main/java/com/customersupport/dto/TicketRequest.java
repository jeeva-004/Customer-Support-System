package com.customersupport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class TicketRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    @NotNull(message = "Status ID is required")
    private String status;
    @NotNull(message = "Priority ID is required")
    private String priority;
    private Long customerId;
    private Long assignedAgentId;
    private Long categoryId;
}