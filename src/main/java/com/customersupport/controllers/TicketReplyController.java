package com.customersupport.controllers;

import com.customersupport.entities.TicketReply;
import com.customersupport.services.TicketReplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticketreplies")
public class TicketReplyController {
    private final TicketReplyService ticketReplyService;

    TicketReplyController(TicketReplyService ticketReplyService){
        this.ticketReplyService = ticketReplyService;
    }

    @GetMapping
    public List<TicketReply> getAll(){
        return ticketReplyService.getAll();
    }

    @GetMapping("/{id}")
    public TicketReply getById(@PathVariable Long id){
        return ticketReplyService.getById(id);
    }

    @PostMapping
    public String createTicketReply(@RequestBody TicketReply ticketReply){
        ticketReplyService.createTicketReply(ticketReply);
        return "Ticket Reply created";
    }

    @PutMapping("/{id}")
    public String updateTicketReply(@RequestBody TicketReply newTicketReply, @PathVariable Long id){
        ticketReplyService.updateTicketReply(newTicketReply, id);
        return "Ticket Reply Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteTickteReply(@PathVariable Long id){
        ticketReplyService.deleteTicketReply(id);

        return "Ticket Reply Deleted";
    }
}
