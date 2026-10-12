package com.customersupport.services;

import com.customersupport.dto.TicketReplyRequest;
import com.customersupport.dto.TicketReplyResponse;
import com.customersupport.entities.TicketReply;
import com.customersupport.exceptions.ResourceNotFoundException;
import com.customersupport.repositories.TicketReplyRepository;
import com.customersupport.repositories.TicketRepository;
import com.customersupport.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketReplyService {
    private final TicketReplyRepository ticketReplyRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    TicketReplyService(TicketReplyRepository ticketReplyRepository
                        ,UserRepository userRepository,
                        TicketRepository ticketRepository){
        this.ticketReplyRepository = ticketReplyRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;

    }

    // Entity to response

    private TicketReplyResponse toResponse(TicketReply ticketReply){

        TicketReplyResponse response = new TicketReplyResponse();

        response.setId(ticketReply.getId());
        response.setMessage(ticketReply.getMessage());
        response.setCreatedAt(ticketReply.getCreatedAt());
        response.setUpdatedAt(ticketReply.getUpdatedAt());
        response.setTicketId(ticketReply.getTicket().getId());
        response.setUserId(ticketReply.getUser().getId());

        return response;
    }


    // request to entity

    private TicketReply toTicketReply(TicketReplyRequest ticketReply){
        TicketReply tReply = new TicketReply();

        tReply.setMessage(ticketReply.getMessage());
        tReply.setUser(userRepository.getReferenceById(ticketReply.getUserId()));
        tReply.setTicket(ticketRepository.getReferenceById(ticketReply.getTicketId()));
        return tReply;
    }

    public List<TicketReplyResponse> getAll(){
        return ticketReplyRepository.findAll().stream().map(this::toResponse).toList();
    }

    public TicketReplyResponse getById(Long id){
        TicketReply response = ticketReplyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TicketReply Not Found with this id"+id));

        return toResponse(response);
    }

    public TicketReplyResponse createTicketReply(TicketReplyRequest ticketReply){
        TicketReply tr = toTicketReply(ticketReply);
        TicketReply savedReply = ticketReplyRepository.save(tr);
        return toResponse(savedReply);
    }

    public TicketReplyResponse updateTicketReply(TicketReplyRequest ticketReply, Long id){
        TicketReply tr = ticketReplyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TicketReply Not Found with this id"+id));

        tr.setMessage(ticketReply.getMessage());
        TicketReply updatedReply = ticketReplyRepository.save(tr);
        return toResponse(updatedReply);
    }

    public String deleteTicketReply(Long id){
        ticketReplyRepository.deleteById(id);
        return "Ticket Reply Deleted";
    }

    public List<TicketReplyResponse> getByTicketId(Long ticketId) {
        return ticketReplyRepository
                .findByTicket_IdOrderByCreatedAtAsc(ticketId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
