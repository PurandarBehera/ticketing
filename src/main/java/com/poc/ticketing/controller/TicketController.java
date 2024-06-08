package com.poc.ticketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.ticketing.dto.TicketPurchaseDTO;
import com.poc.ticketing.entity.Ticket;
import com.poc.ticketing.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@PostMapping("/purchase")
	public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketPurchaseDTO ticketPurchaseDTO) {
		Ticket ticket = ticketService.purchaseTicket(ticketPurchaseDTO);
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		Ticket ticket = ticketService.getTicket(id);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@GetMapping("/section/{section}")
	public ResponseEntity<List<Ticket>> getTicketsBySection(@PathVariable String section) {
		List<Ticket> tickets = ticketService.getTicketsBySection(section);
		if (tickets.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeTicket(@PathVariable Long id) {
		ticketService.removeTicket(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}/update-section")
	public ResponseEntity<Ticket> updateSeat(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
		Ticket ticket = ticketService.updateSeat(id, updatedTicket);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
}
