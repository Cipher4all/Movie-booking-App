package com.abhishek.MovieBooking.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MovieScreening {
	private String movieName;
    private String moviePosterURL;
    private long theatreId;
    private String theatreName;
    private String theatreCity;
    private String screeningDate;
    private String screeningTime;
    private int numSeats;
    private Long screenId;
}
