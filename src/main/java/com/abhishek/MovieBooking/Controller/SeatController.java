package com.abhishek.MovieBooking.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.MovieBooking.DTO.TicketBooking;
import com.abhishek.MovieBooking.Model.Screen;
import com.abhishek.MovieBooking.Model.Seat;
import com.abhishek.MovieBooking.Model.Ticket;
import com.abhishek.MovieBooking.Repository.ScreenRepository;
import com.abhishek.MovieBooking.Repository.SeatRepository;
import com.abhishek.MovieBooking.Repository.TicketRepository;
import com.abhishek.MovieBooking.Services.PdfGenerator;
import com.abhishek.MovieBooking.Services.ScreenService;
import com.abhishek.MovieBooking.Services.SeatService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class SeatController {

	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	public SeatController(SeatService seatService, ScreenService screenService, SeatRepository seatRepository, TicketRepository ticketRepository) {
		this.seatService = seatService;
		this.screenService = screenService;
		this.seatRepository = seatRepository;
		this.ticketRepository = ticketRepository;
	}
	
	@PostMapping("/addseats/{id}")
	public String addAllSeatstoScreen(@PathVariable long id, Seat seat){
		Optional<Screen> screenOptional = screenService.getById(id);
		if(screenOptional.isPresent()) {
			Screen screen = screenOptional.get();
			seatService.addAllSeats(screen, seat);
			return "Seats added to respective screen successfully";
		}else {
			return "value is absent";
		}
	}
	
	@GetMapping("/seat/{id}")
	public String getseatsByScreenId(@PathVariable long id, Model model) {
		Optional<Screen> optionalscreen = screenService.getById(id);
		if(optionalscreen.isPresent()) {
			Screen screen = optionalscreen.get();
			model.addAttribute("screen", screen);
		}
		Ticket ticket = new Ticket();
		List<Ticket> tickets = ticketRepository.findByScreeningId(id);
		List<Seat> seats = seatRepository.findByScreenId(id);
		model.addAttribute("ticket", ticket);
		model.addAttribute("seats", seats);
		model.addAttribute("tickets", tickets);
		return "seats";
	}
	
	@PostMapping("/seat/{id}")
	public String getbookedSeats(Model model, @ModelAttribute Ticket ticket, @PathVariable(value = "id") Long id) {
		if(isAuthenticated()) {
		Optional<Screen> optionalscreen = screenService.getById(id);
		if(optionalscreen.isPresent()) {
			Screen screen = optionalscreen.get();
			model.addAttribute("screen", screen);
		}
		ticket.setScreeningId(id);
		ticketRepository.save(ticket);
		List<Seat> seats = seatRepository.findByScreenId(id);
		List<Ticket> tickets = ticketRepository.findByScreeningId(id);
		for(Seat s: seats) {
			for(Ticket t: tickets) {
				List<Integer> seatNum = new ArrayList<Integer>();
				seatNum.addAll(s.getSeat_num());
				if(s.getSeat_id() == t.getSeatId()) {
					System.out.println(s.getSeat_id());
					System.out.println(t.getSeatNum());
					seatNum.replaceAll(e -> e == t.getSeatNum() ? 0 : e);
					s.setSeat_num(seatNum);
					System.out.println(s.getSeat_num());
					seatRepository.save(s);
				}
			}
		}
		model.addAttribute("ticket", ticket);
		return "ticket_success";
		}
		return "redirect:/login";
	}
	
	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}
	
	@GetMapping("/export-to-pdf")
	  public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException 
	  {
	    response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	    Ticket ticket = ticketRepository.findFirstByOrderByTicketIdDesc();
	    PdfGenerator generator = new PdfGenerator();
	    generator.generate(ticket, response);
	  }
}
