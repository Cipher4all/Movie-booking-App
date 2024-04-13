package com.abhishek.MovieBooking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "screening")
public class Screening {

	@Id
    @Column(name = "SCREENING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long screeningId;
    @Column(name = "THEATRE_ID")
    private long theatreId;
    @Column(name = "SCREEN_ID")
    private long screenId;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "SCREENING_DATE")
    private java.sql.Date screeningDate;
    @Column(name = "SCREENING_TIME")
    private java.sql.Time screeningTime;
    @Column(name = "BOOKED_TICKETS")
    private int bookedTickets;
    @Column(name = "CITY_NAME")
    private String cityName;
}
