package com.abhishek.MovieBooking.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long>{
	List<Screening> findByScreeningDate(Date screeningDate);
    List<Screening> findByMovieNameAndCityName(String cityName, String movieName);

}
