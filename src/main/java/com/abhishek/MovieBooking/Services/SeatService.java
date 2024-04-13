package com.abhishek.MovieBooking.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.MovieBooking.DTO.TicketBooking;
import com.abhishek.MovieBooking.Model.Screen;
import com.abhishek.MovieBooking.Model.Seat;
import com.abhishek.MovieBooking.Repository.ScreenRepository;
import com.abhishek.MovieBooking.Repository.SeatRepository;

@Service
@Transactional
public class SeatService {

	private SeatRepository seatRepository;
	private ScreenRepository screenRepository;
	
	public SeatService(SeatRepository seatRepository, ScreenRepository screenRepository) {
		this.seatRepository = seatRepository;
		this.screenRepository = screenRepository;
	}
	
	public Collection<Seat> addAllSeats(Screen screen, Seat seat){
		Map<Integer, Seat> map = new HashMap<>();
		List<Character> row_ids = Arrays.asList('A', 'B', 'C', 'D', 'E');
			int total_seats = screen.getSeatsNum();
			int row_seats = total_seats / row_ids.size();
			List<Integer> seats = new ArrayList<Integer>(row_seats);
			for(int j = 1; j < row_seats + 1; j++) {
				seats.add(j);
			}
			System.out.println(seats);
			for(int i = 0; i < row_ids.size(); i++) {
				Seat seat1 = new Seat();
				seat1.setRow_id(row_ids.get(i));
				seat1.setScreenId(screen.getScreenId());
				seat1.setSeat_num(seats);
				map.put(i, seat1);
				System.out.println(map.values());
			}
		return seatRepository.saveAll(map.values());
	}
	
	public TicketBooking bookSeats(Seat seat) {
			TicketBooking ticketBooking =  new TicketBooking();
			ticketBooking.setScreenId(seat.getScreenId());
			ticketBooking.setRowId(seat.getRow_id());
			ticketBooking.setSeatSelected(seat.getSeat_num());
			ticketBooking.setSeatsBooked(seat.getSeat_num().size());	
			return ticketBooking;
	}
}
