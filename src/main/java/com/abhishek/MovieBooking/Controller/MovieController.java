 package com.abhishek.MovieBooking.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.MovieBooking.Model.City;
import com.abhishek.MovieBooking.Model.Movie;
import com.abhishek.MovieBooking.Repository.CityRepository;
import com.abhishek.MovieBooking.Repository.MovieRepository;
import com.abhishek.MovieBooking.Services.MovieService;

@Controller
public class MovieController {

	private MovieRepository movieRepository;
	private CityRepository cityRepository;
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieRepository movieRepository, CityRepository cityRepository, MovieService movieService) {
		this.movieRepository = movieRepository;
		this.cityRepository = cityRepository;
		this.movieService = movieService;
	}
	
	@GetMapping("/moviebycity/{cityname}")
	public String getMoviesList(@PathVariable String cityname, Model model) throws Exception{
		Optional<City> optioanlCity = cityRepository.findById(cityname);
		if(optioanlCity.isPresent()) {
			List<Movie> mvlist = movieRepository.findBycityname(cityname);
			model.addAttribute("movies", mvlist);
			return "moviesPage";
		}else {
			throw new Exception("City is not present");
		}
	}
	
	@PostMapping("/newMovie")
	public Collection<Movie> addNewMovie(@RequestBody Movie movie) {
		return movieService.addNewMovie(movie);
	}
	
	@GetMapping("/movielist/{id}")
	public String deleteMovie(@PathVariable Long id) {
		Optional<Movie> optionalMovie = movieService.getById(id);
		if(optionalMovie.isPresent()) {
			Movie movie = optionalMovie.get();
			movieRepository.delete(movie);
			return "Successfully deleted movie";
		}else {
			return "movie not present";
		}
	}
}
