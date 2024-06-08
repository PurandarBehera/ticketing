package com.poc.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.ticketing.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findBySeatSection(String seatSection);
}
