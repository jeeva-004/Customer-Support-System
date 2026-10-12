package com.customersupport.controllers;

import com.customersupport.dto.TicketRequest;
import com.customersupport.dto.TicketResponse;
import com.customersupport.entities.Ticket;
import com.customersupport.enums.TicketPriority;
import com.customersupport.enums.TicketStatus;
import com.customersupport.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;

    TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponse> getAll(){
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id){
        return ticketService.getById(id);
    }

    @PostMapping
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest ticket){
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public TicketResponse updateTicket(@Valid @RequestBody TicketRequest ticket, @PathVariable Long id){
        return ticketService.updateTicket(ticket, id);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return "Ticket Deleted";
    }

    @GetMapping("/customer/{customerId}")
    public List<TicketResponse> getByCustomerId(
            @PathVariable Long customerId) {

        return ticketService.getByCustomerId(customerId);
    }

    @GetMapping("/agent/{agentId}")
    public List<TicketResponse> getByAssignedAgentId(
            @PathVariable Long agentId) {

        return ticketService.getByAssignedAgentId(agentId);
    }

    @GetMapping("/status/{status}")
    public List<TicketResponse> getByStatus(
            @PathVariable TicketStatus status) {

        return ticketService.getByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<TicketResponse> getByPriority(
            @PathVariable TicketPriority priority) {

        return ticketService.getByPriority(priority);
    }

    @GetMapping("/category/{categoryId}")
    public List<TicketResponse> getByCategoryId(
            @PathVariable Long categoryId) {

        return ticketService.getByCategoryId(categoryId);
    }

    @GetMapping("/customer/{customerId}/status/{status}")
    public List<TicketResponse> getCustomerTicketsByStatus(
            @PathVariable Long customerId,
            @PathVariable TicketStatus status) {

        return ticketService.getCustomerTicketsByStatus(customerId, status);
    }

    @GetMapping("/agent/{agentId}/status/{status}")
    public List<TicketResponse> getAgentTicketsByStatus(
            @PathVariable Long agentId,
            @PathVariable TicketStatus status) {

        return ticketService.getAgentTicketsByStatus(agentId, status);
    }

}
