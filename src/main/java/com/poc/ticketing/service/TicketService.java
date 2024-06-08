package com.poc.ticketing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.ticketing.dto.TicketPurchaseDTO;
import com.poc.ticketing.entity.AppUser;
import com.poc.ticketing.entity.Ticket;
import com.poc.ticketing.repository.AppUserRepository;
import com.poc.ticketing.repository.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AppUserRepository appUserRepository;

	public Ticket purchaseTicket(TicketPurchaseDTO ticketPurchaseDTO) {
		AppUser user = appUserRepository.findByEmail(ticketPurchaseDTO.getUserEmail());
		if (user == null) {
			throw new IllegalArgumentException("User not found with email: " + ticketPurchaseDTO.getUserEmail());
		}

		Ticket ticket = new Ticket();
		ticket.setFromLocation(ticketPurchaseDTO.getFromLocation());
		ticket.setToLocation(ticketPurchaseDTO.getToLocation());
		ticket.setPrice(ticketPurchaseDTO.getPrice());
		ticket.setSeatSection(ticketPurchaseDTO.getSeatSection());
		ticket.setUser(user);

		// Find an available seat number
		int seatNumber = findAvailableSeatNumber(ticketPurchaseDTO.getSeatSection());
		ticket.setSeatNumber(seatNumber);

		return ticketRepository.save(ticket);
	}

	private int findAvailableSeatNumber(String seatSection) {
		List<Ticket> tickets = ticketRepository.findBySeatSection(seatSection);
		int seatNumber = 1;
		for (Ticket ticket : tickets) {
			if (ticket.getSeatNumber() == seatNumber) {
				seatNumber++;
			} else {
				break;
			}
		}
		return seatNumber;
	}

	public Ticket getTicket(Long id) {
		Optional<Ticket> ticketOpt = ticketRepository.findById(id);
		return ticketOpt.orElse(null);
	}

	public List<Ticket> getTicketsBySection(String section) {
		return ticketRepository.findBySeatSection(section);
	}

	public void removeTicket(Long id) {
		ticketRepository.deleteById(id);
	}

	public Ticket updateSeat(Long id, Ticket updatedTicket) {
		Ticket ticket = getTicket(id);
		if (ticket == null) {
			throw new IllegalArgumentException("Ticket not found");
		}
		ticket.setSeatSection(updatedTicket.getSeatSection());
		ticket.setSeatNumber(updatedTicket.getSeatNumber());
		return ticketRepository.save(ticket);
	}
}
