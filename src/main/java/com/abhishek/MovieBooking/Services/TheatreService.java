package com.abhishek.MovieBooking.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.MovieBooking.Model.Theatre;
import com.abhishek.MovieBooking.Repository.TheatreRepository;

@Service
@Transactional
public class TheatreService {

	private TheatreRepository theatreRepository;
	
	public TheatreService(TheatreRepository theatreRepository) {
		this.theatreRepository = theatreRepository;
	}
	
	public Theatre addNewTheatre(Theatre theatre) {
		return theatreRepository.save(theatre);
	}
	
	public Optional<Theatre> findById(Long id){
		return theatreRepository.findById(id);
	}
}
