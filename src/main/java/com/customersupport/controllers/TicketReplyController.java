package com.customersupport.controllers;

import com.customersupport.dto.TicketReplyRequest;
import com.customersupport.dto.TicketReplyResponse;
import com.customersupport.entities.TicketReply;
import com.customersupport.services.TicketReplyService;
import jakarta.validation.Valid;
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
    public List<TicketReplyResponse> getAll(){
        return ticketReplyService.getAll();
    }

    @GetMapping("/{id}")
    public TicketReplyResponse getById(@PathVariable Long id){
        return ticketReplyService.getById(id);
    }

    @PostMapping
    public TicketReplyResponse createTicketReply(@Valid @RequestBody TicketReplyRequest ticketReply){
        return ticketReplyService.createTicketReply(ticketReply);
    }

    @PutMapping("/{id}")
    public TicketReplyResponse updateTicketReply(@Valid @RequestBody TicketReplyRequest newTicketReply, @PathVariable Long id){
        return ticketReplyService.updateTicketReply(newTicketReply, id);

    }

    @DeleteMapping("/{id}")
    public String deleteTickteReply(@PathVariable Long id){
        ticketReplyService.deleteTicketReply(id);

        return "Ticket Reply Deleted";
    }

    @GetMapping("/ticket/{ticketId}")
    public List<TicketReplyResponse> getByTicketId(
            @PathVariable Long ticketId) {
        return ticketReplyService.getByTicketId(ticketId);
    }
}
