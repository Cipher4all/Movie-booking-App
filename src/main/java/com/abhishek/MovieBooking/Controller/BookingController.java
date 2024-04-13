package com.abhishek.MovieBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhishek.MovieBooking.Repository.SeatRepository;
import com.abhishek.MovieBooking.Services.ScreenService;
import com.abhishek.MovieBooking.Services.SeatService;

@Controller
@RequestMapping("/api/movies")
public class BookingController {

	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	public BookingController(SeatService seatService, ScreenService screenService, SeatRepository seatRepository) {
		this.seatService = seatService;
		this.screenService = screenService;
		this.seatRepository = seatRepository;
	}
}
