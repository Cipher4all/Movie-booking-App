package com.abhishek.MovieBooking.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.MovieBooking.Model.Movie;
import com.abhishek.MovieBooking.Model.Screen;
import com.abhishek.MovieBooking.Model.Theatre;
import com.abhishek.MovieBooking.Repository.ScreenRepository;
import com.abhishek.MovieBooking.Repository.TheatreRepository;

@Service
@Transactional
public class ScreenService {

	private TheatreRepository theatreRepository;
	private ScreenRepository screenRepository;
	private TheatreService theatreService;
	
	public ScreenService(TheatreRepository theatreRepository, ScreenRepository screenRepository, TheatreService theatreService) {
		this.screenRepository = screenRepository;
		this.theatreRepository = theatreRepository;
		this.theatreService = theatreService;
	}
	
	public List<Screen> addScreentoTheatre(Screen screen, Long theatreId){
		Optional<Theatre> theatreOptional = theatreService.findById(theatreId);
		Map<Integer, Screen> map = new HashMap<>();
		if(theatreOptional.isPresent()) {
			Theatre theatre = theatreOptional.get();
			for(int i = 0; i<theatre.getTotalScreens(); i++) {
				Screen screen1 = new Screen();
				screen1.setTheatreId(theatre.getTheatreId());
				screen1.setScreenId(screen.getScreenId());
				screen1.setSeatsNum(screen.getSeatsNum());
				map.put(i, screen1);
			}
		}
		return screenRepository.saveAll(map.values());
	}
	
	public Optional<Screen> getById(long screenId){
		return screenRepository.findById(screenId);
	}
}
