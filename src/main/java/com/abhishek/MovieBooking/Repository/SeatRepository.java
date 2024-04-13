package com.abhishek.MovieBooking.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhishek.MovieBooking.Model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

	@Query(value = "select * from seat inner join seat_seat_num"
			+ " on seat.seat_id = seat_seat_num.seat_seat_id"
			+ " inner join current_date_operation"
			+ " on seat.operation_date_id = current_date_operation.date_id"
			+ " where show_date = ? ", nativeQuery = true)
	public List<Seat> getAllByDate(LocalDate date);
	public List<Seat> findByScreenId(long screenId);
}
