package com.abhishek.MovieBooking.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhishek.MovieBooking.Model.City;
import com.abhishek.MovieBooking.Repository.CityRepository;

@Controller
public class HomeController {

	
	@Autowired
	CityRepository cityRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		List<City> cities = cityRepository.findAll();
		model.addAttribute("cities", cities);
		return "home";
	}
}
