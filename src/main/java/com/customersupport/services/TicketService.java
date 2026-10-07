package com.customersupport.services;

import com.customersupport.entities.Ticket;
import com.customersupport.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public Ticket getById(Long id){
        return ticketRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+ id));
    }

    public String createTicket(Ticket ticket){
        ticketRepository.save(ticket);

        return "Ticket Added";
    }

    public String updateTicket(Ticket ticket, Long id){
        Ticket t = ticketRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));

        t.setTitle(ticket.getTitle());
        t.setDescription(ticket.getDescription());
        t.setUpdatedAt(ticket.getUpdatedAt());
        t.setPriority(ticket.getPriority());
        t.setStatus(ticket.getStatus());
        t.setAssignedAgent(ticket.getAssignedAgent());
        t.setCategory(ticket.getCategory());
        t.setCustomer(ticket.getCustomer());

        ticketRepository.save(t);

        return "Ticket Updated";
    }

    public String deleteTicket(Long id){
        ticketRepository.deleteById(id);

        return "Ticket Deleted";
    }
}
