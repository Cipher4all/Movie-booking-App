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
@Table(name = "theatre")
public class Theatre {

	@Id
    @Column(name = "THEATRE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theatreId;
    @Column(name = "THEATRE_NAME")
    private String theatreName;
    @Column(name = "THEATRE_CITY")
    private String theatreCity;
    @Column(name = "TOTAL_SCREENS")
    private int totalScreens;
}
