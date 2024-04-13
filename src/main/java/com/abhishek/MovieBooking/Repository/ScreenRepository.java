package com.abhishek.MovieBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>{
	public Screen findByScreenId(long screenId);
    public List<Screen> findByTheatreId(long theatreId);
}
