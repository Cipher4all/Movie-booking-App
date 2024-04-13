package com.abhishek.MovieBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.MovieBooking.Model.Theatre;
import com.abhishek.MovieBooking.Services.TheatreService;

@RestController
@RequestMapping("/api/movies")
public class TheatreController {

	private TheatreService theatreService;
	
	@Autowired
	public TheatreController(TheatreService theatreService) {
		this.theatreService = theatreService;
	}
	
	@PostMapping("/newTheatre")
	public String addNewTheatre(@RequestBody Theatre theatre) {
		theatreService.addNewTheatre(theatre);
		return "redirect:/theatre/" + theatre.getTheatreId();
	}
}
