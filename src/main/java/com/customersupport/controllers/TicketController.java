package com.customersupport.controllers;

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
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id){
        return ticketService.getById(id);
    }

    @PostMapping
    public String createTicket(@RequestBody Ticket ticket){
        ticketService.createTicket(ticket);
        return "Ticket Created";
    }

    @PutMapping("/{id}")
    public String updateTicket(@RequestBody Ticket ticket, @PathVariable Long id){
        ticketService.updateTicket(ticket, id);
        return "Ticket Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return "Ticket Deleted";
    }

}
