package com.customersupport.services;

import com.customersupport.entities.Category;
import com.customersupport.entities.Ticket;
import com.customersupport.entities.User;
import com.customersupport.repositories.CategoryRepository;
import com.customersupport.repositories.TicketRepository;
import com.customersupport.repositories.UserRepository;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    TicketService(TicketRepository ticketRepository,
                CategoryRepository categoryRepository,
                  UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public Ticket getById(Long id){
        return ticketRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record Not Found"));
    }

    public String createTicket(Ticket ticket) {

        Category category = categoryRepository.findById(
                ticket.getCategory().getId()
        ).orElseThrow(() ->
                new NoSuchElementException("Category not found")
        );

        User customer = userRepository.findById(
                ticket.getCustomer().getId()
        ).orElseThrow(() ->
                new NoSuchElementException("Customer not found")
        );

        ticket.setCategory(category);
        ticket.setCustomer(customer);

        if (ticket.getAssignedAgent() != null) {
            User agent = userRepository.findById(
                    ticket.getAssignedAgent().getId()
            ).orElseThrow(() ->
                    new NoSuchElementException("Agent not found")
            );

            ticket.setAssignedAgent(agent);
        }

        ticketRepository.save(ticket);

        return "Ticket Added";
    }

    public String updateTicket(Ticket ticket, Long id) {

        Ticket t = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Ticket not found: " + id)
                );

        t.setTitle(ticket.getTitle());
        t.setDescription(ticket.getDescription());
        t.setPriority(ticket.getPriority());
        t.setStatus(ticket.getStatus());

        if (ticket.getCategory() != null) {
            Category category = categoryRepository.findById(
                    ticket.getCategory().getId()
            ).orElseThrow(() ->
                    new NoSuchElementException("Category not found")
            );

            t.setCategory(category);
        }

        if (ticket.getCustomer() != null) {
            User customer = userRepository.findById(
                    ticket.getCustomer().getId()
            ).orElseThrow(() ->
                    new NoSuchElementException("Customer not found")
            );

            t.setCustomer(customer);
        }

        if (ticket.getAssignedAgent() != null) {
            User agent = userRepository.findById(
                    ticket.getAssignedAgent().getId()
            ).orElseThrow(() ->
                    new NoSuchElementException("Agent not found")
            );

            t.setAssignedAgent(agent);
        }

        ticketRepository.save(t);

        return "Ticket Updated";
    }

    public String deleteTicket(Long id){
        ticketRepository.deleteById(id);

        return "Ticket Deleted";
    }
}
