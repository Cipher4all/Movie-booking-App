package com.abhishek.MovieBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	List<Ticket> findByScreeningId(long screeningId);
	Ticket findFirstByOrderByTicketIdDesc();
}
