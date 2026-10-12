package com.customersupport.services;

import com.customersupport.dto.TicketReplyResponse;
import com.customersupport.dto.TicketRequest;
import com.customersupport.dto.TicketResponse;
import com.customersupport.entities.Ticket;
import com.customersupport.entities.TicketReply;
import com.customersupport.enums.TicketPriority;
import com.customersupport.enums.TicketStatus;
import com.customersupport.exceptions.ResourceNotFoundException;
import com.customersupport.repositories.CategoryRepository;
import com.customersupport.repositories.TicketRepository;
import com.customersupport.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    TicketService(TicketRepository ticketRepository,
                  CategoryRepository categoryRepository,
                  UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;    }

    // Entity to response

    private TicketResponse toResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();

        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus().name());
        response.setPriority(ticket.getPriority().name());
        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());

        response.setCustomerId(ticket.getCustomer().getId());

        response.setAssignedAgentId(
                ticket.getAssignedAgent() != null
                        ? ticket.getAssignedAgent().getId()
                        : null
        );

        response.setCategoryId(ticket.getCategory().getId());

        response.setReplies(
                ticket.getReplies() == null
                        ? List.of()
                        : ticket.getReplies().stream()
                        .map(this::toReplyResponse)
                        .toList()
        );

        return response;
    }

    // TicketReply entity to response

    private TicketReplyResponse toReplyResponse(TicketReply ticketReply) {
        TicketReplyResponse response = new TicketReplyResponse();

        response.setId(ticketReply.getId());
        response.setMessage(ticketReply.getMessage());
        response.setCreatedAt(ticketReply.getCreatedAt());
        response.setUpdatedAt(ticketReply.getUpdatedAt());
        response.setTicketId(ticketReply.getTicket().getId());
        response.setUserId(ticketReply.getUser().getId());

        return response;
    }

    // Request to entity

    private Ticket toTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();

        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus(TicketStatus.valueOf(ticketRequest.getStatus().toUpperCase()));
        ticket.setPriority(TicketPriority.valueOf(ticketRequest.getPriority().toUpperCase()));

        if (ticketRequest.getCustomerId() != null) {
            userRepository.getReferenceById(ticketRequest.getCustomerId());
        } else {
            ticketRequest.setCustomerId(null);
        }

        if (ticketRequest.getCategoryId() != null) {
            categoryRepository.getReferenceById(ticketRequest.getCategoryId());
        } else {
            ticketRequest.setCategoryId(null);
        }

        if (ticketRequest.getAssignedAgentId() != null) {
            ticket.setAssignedAgent(
                    userRepository.getReferenceById(
                            ticketRequest.getAssignedAgentId()
                    )
            );
        } else {
            ticket.setAssignedAgent(null);
        }
        return ticket;
    }

    public List<TicketResponse> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TicketResponse getById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket Not Found with this id"+id)
                );

        return toResponse(ticket);
    }

    public TicketResponse createTicket(TicketRequest ticketRequest) {
        Ticket ticket = toTicket(ticketRequest);

        Ticket savedTicket = ticketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    public TicketResponse updateTicket(TicketRequest ticketRequest, Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket Not Found with this id"+id)
                );

        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus(TicketStatus.valueOf(ticketRequest.getStatus().toUpperCase()));
        ticket.setPriority(TicketPriority.valueOf(ticketRequest.getPriority().toUpperCase()));

        if (ticketRequest.getCustomerId() != null) {
            ticket.setCustomer(
                    userRepository.getReferenceById(
                            ticketRequest.getCustomerId()
                    )
            );
        }

        if (ticketRequest.getCategoryId() != null) {
            ticket.setCategory(
                    categoryRepository.getReferenceById(
                            ticketRequest.getCategoryId()
                    )
            );
        }

        if (ticketRequest.getAssignedAgentId() != null) {
            ticket.setAssignedAgent(
                    userRepository.getReferenceById(
                            ticketRequest.getAssignedAgentId()
                    )
            );
        }

        Ticket updatedTicket = ticketRepository.save(ticket);

        return toResponse(updatedTicket);
    }

    public String deleteTicket(Long id) {
        ticketRepository.deleteById(id);

        return "Ticket Deleted";
    }

    public List<TicketResponse> getByCustomerId(Long customerId) {
        return ticketRepository.findByCustomer_Id(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getByAssignedAgentId(Long agentId) {
        return ticketRepository.findByAssignedAgent_Id(agentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getByPriority(TicketPriority priority) {
        return ticketRepository.findByPriority(priority)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getByCategoryId(Long categoryId) {
        return ticketRepository.findByCategory_Id(categoryId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getCustomerTicketsByStatus(
            Long customerId, TicketStatus status) {

        return ticketRepository.findByCustomer_IdAndStatus(customerId, status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TicketResponse> getAgentTicketsByStatus(
            Long agentId, TicketStatus status) {

        return ticketRepository.findByAssignedAgent_IdAndStatus(agentId, status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

}