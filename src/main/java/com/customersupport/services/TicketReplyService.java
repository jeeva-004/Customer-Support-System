package com.customersupport.services;

import com.customersupport.entities.TicketReply;
import com.customersupport.repositories.TicketReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketReplyService {
    private final TicketReplyRepository ticketReplyRepository;

    TicketReplyService(TicketReplyRepository ticketReplyRepository){
        this.ticketReplyRepository = ticketReplyRepository;
    }

    public List<TicketReply> getAll(){
        return ticketReplyRepository.findAll();
    }

    public TicketReply getById(Long id){
        return ticketReplyRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
    }

    public String createTicketReply(TicketReply ticketReply){
        ticketReplyRepository.save(ticketReply);

        return "Ticket Reply added";
    }

    public String updateTicketReply(TicketReply ticketReply, Long id){
        TicketReply tr = ticketReplyRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
        tr.setMessage(ticketReply.getMessage());
        tr.setUpdatedAt(ticketReply.getUpdatedAt());
        ticketReplyRepository.save(tr);
        return "Ticket Reply updated";
    }

    public String deleteTicketReply(Long id){
        ticketReplyRepository.deleteById(id);
        return "Ticket Reply Deleted";
    }
}
