package com.customersupport.repositories;

import com.customersupport.entities.TicketReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketReplyRepository extends JpaRepository<TicketReply, Long> {
    List<TicketReply> findByTicket_IdOrderByCreatedAtAsc(Long id);
}
