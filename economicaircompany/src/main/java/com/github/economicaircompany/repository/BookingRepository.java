package com.github.economicaircompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.economicaircompany.model.Booking;
import com.github.economicaircompany.model.Flight;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    /* 1 */ public Booking findByBookingCode(String bookingCode);

    /* 2 */ public List<Booking> findByFlightAndSeatNumber(Flight flight, String seatNumber);

}
