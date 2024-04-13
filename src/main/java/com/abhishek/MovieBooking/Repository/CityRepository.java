package com.abhishek.MovieBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.City;

@Repository
public interface CityRepository extends JpaRepository<City, String>{

}
