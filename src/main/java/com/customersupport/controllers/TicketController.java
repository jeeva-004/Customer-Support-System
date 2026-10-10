package com.customersupport.controllers;

import com.customersupport.dto.TicketRequest;
import com.customersupport.dto.TicketResponse;
import com.customersupport.entities.Ticket;
import com.customersupport.services.TicketService;
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
    public TicketResponse createTicket(@RequestBody TicketRequest ticket){
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public TicketResponse updateTicket(@RequestBody TicketRequest ticket, @PathVariable Long id){
        return ticketService.updateTicket(ticket, id);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return "Ticket Deleted";
    }

}
