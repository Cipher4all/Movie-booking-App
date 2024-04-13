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
@Table(name = "city")
public class City {

	@Id
    @Column(name = "city_name")
    private String cityname;

}