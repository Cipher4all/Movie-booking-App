package com.abhishek.MovieBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>{

	Theatre findByTheatreId(Long theatreId);
	Theatre findByTheatreNameAndTheatreCity(String theatreName, String theatreCity);
}
