package com.abhishek.MovieBooking.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "seat")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEAT_ID")
	private long seat_id;
    
    @Column(name = "ROW_ID")
    private char row_id;
    
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seats", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "SEAT_NUM", nullable = false)
    private List<Integer> seat_num;
    
    @Column(name = "SCREEN_ID")
    private long screenId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CurrentDateOperation operation;
}
