package com.abhishek.MovieBooking.DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class TicketBooking {
	
	private char rowId;
	private List<Integer> seatSelected;
	private long screenId;
	private int seatsBooked;
}
