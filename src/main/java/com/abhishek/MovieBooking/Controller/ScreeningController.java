package com.abhishek.MovieBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.MovieBooking.DTO.MovieScreening;
import com.abhishek.MovieBooking.Repository.MovieRepository;
import com.abhishek.MovieBooking.Services.Screeningservice;

@Controller
public class ScreeningController {

	@Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Screeningservice screeningservice;
    
    @GetMapping("/moviebycity/{cityname}/{moviename}")
    public String getScreenings(@PathVariable String cityname, @PathVariable String moviename, Model model) {
    	List<MovieScreening> result = this.screeningservice.getMovieScreeningsByMovieAndCity(cityname, moviename);
    	model.addAttribute("Totalscreens", result);
    	model.addAttribute("Moviename", moviename);
    	return "screenings";
    }
}
