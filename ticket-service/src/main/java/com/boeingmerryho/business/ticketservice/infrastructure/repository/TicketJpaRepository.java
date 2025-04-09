package com.boeingmerryho.business.ticketservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boeingmerryho.business.ticketservice.domain.Ticket;

public interface TicketJpaRepository extends JpaRepository<Ticket, Long> {
}
