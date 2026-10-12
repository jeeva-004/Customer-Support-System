package com.customersupport.repositories;

import com.customersupport.entities.Ticket;
import com.customersupport.enums.TicketPriority;
import com.customersupport.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer_Id(Long id);
    List<Ticket> findByAssignedAgent_Id(Long id);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByPriority(TicketPriority priority);
    List<Ticket> findByCategory_Id(Long id);
    List<Ticket> findByCustomer_IdAndStatus(Long id, TicketStatus status);
    List<Ticket> findByAssignedAgent_IdAndStatus(Long id, TicketStatus status);
}
