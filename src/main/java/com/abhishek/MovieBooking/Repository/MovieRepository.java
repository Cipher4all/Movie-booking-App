package com.abhishek.MovieBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findBycityname(String cityname);
	Movie findByMovieName(String movieName);
    Movie findByMovieId(long movieId);
}
