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
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private long movieId;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_POSTER_URL")
    private String moviePosterUrl;
    @Column(name = "MOVIE_TAGS")
    private String movieTags;
    @Column(name = "CITY_NAME")
    private String cityname;
}