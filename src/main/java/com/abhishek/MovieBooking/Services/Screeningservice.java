package com.abhishek.MovieBooking.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.MovieBooking.DTO.MovieScreening;
import com.abhishek.MovieBooking.Model.Movie;
import com.abhishek.MovieBooking.Model.Screen;
import com.abhishek.MovieBooking.Model.Screening;
import com.abhishek.MovieBooking.Model.Theatre;
import com.abhishek.MovieBooking.Repository.MovieRepository;
import com.abhishek.MovieBooking.Repository.ScreenRepository;
import com.abhishek.MovieBooking.Repository.ScreeningRepository;
import com.abhishek.MovieBooking.Repository.TheatreRepository;
import com.abhishek.MovieBooking.Repository.TicketRepository;

@Service
@Transactional
public class Screeningservice {
	
	private ScreeningRepository screeningRepository;
    private MovieRepository movieRepository;
    private TheatreRepository theatreRepository;
    private TicketRepository ticketRepository;
    private ScreenRepository screenRepository;
    
    public Screeningservice(ScreeningRepository screeningRepository, MovieRepository movieRepository, TheatreRepository theatreRepository
            , TicketRepository ticketRepository, ScreenRepository screenRepository) {
    	this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
        this.ticketRepository = ticketRepository;
        this.screenRepository = screenRepository;
    }

    public List<MovieScreening> getMovieScreeningsByMovieAndCity(String cityName, String movieName){
    	Iterable<Screening> screenings = this.screeningRepository.findByMovieNameAndCityName(movieName, cityName);
    	List<MovieScreening> movieScreenings = new ArrayList<>();
    	
    	if(screenings != null) {
    		for(Screening screening : screenings) {
    			MovieScreening movieScreening = new MovieScreening();
    			Theatre theatre = theatreRepository.findByTheatreId(screening.getTheatreId());
    			Movie movie = movieRepository.findByMovieName(movieName);
    			 movieScreening.setMovieName(screening.getMovieName());
                 movieScreening.setMoviePosterURL(movie.getMoviePosterUrl());
    			
    			if(theatre != null) {
    				movieScreening.setTheatreId(theatre.getTheatreId());
                    movieScreening.setTheatreName(theatre.getTheatreName());
                    movieScreening.setTheatreCity(theatre.getTheatreCity());
    			}
    			
    			movieScreening.setScreeningDate(screening.getScreeningDate().toString());
                movieScreening.setScreeningTime(screening.getScreeningTime().toString());
                movieScreening.setScreenId(screening.getScreenId());
                movieScreenings.add(movieScreening);
    		}
    	}
    	
    	return movieScreenings;
    }
}
