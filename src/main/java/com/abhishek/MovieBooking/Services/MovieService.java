package com.abhishek.MovieBooking.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.MovieBooking.Model.City;
import com.abhishek.MovieBooking.Model.Movie;
import com.abhishek.MovieBooking.Repository.CityRepository;
import com.abhishek.MovieBooking.Repository.MovieRepository;

@Service
@Transactional
public class MovieService {

	private MovieRepository movieRepository;
	private CityRepository cityRepository;
	
	public MovieService(MovieRepository movieRepository, CityRepository cityRepository) {
		this.movieRepository = movieRepository;
		this.cityRepository = cityRepository;
	}
	
	public Collection<Movie> addNewMovie(Movie movie) {
		List<City> cities = cityRepository.findAll();
		System.out.println(cities);
		Map<Integer, Movie> map = new HashMap<>();
		for(int i = 0; i < cities.size(); i++) {
			Movie movie1 = new Movie();
			City city = cities.get(i);
			movie1.setCityname(city.getCityname());
			movie1.setMovieId(movie.getMovieId());
			movie1.setMovieName(movie.getMovieName());
			movie1.setMoviePosterUrl(movie.getMoviePosterUrl());
			movie1.setMovieTags(movie.getMovieTags());
			map.put(i, movie1);
		}
		return movieRepository.saveAll(map.values());
	}
	
	public Optional<Movie> getById(Long id){
		return movieRepository.findById(id);
	}
}
