package com.github.economicaircompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.model.Flight;

//@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /* 1 */ public Flight findByFlightCode(String flightCode);

    /* 2 */ public List<Flight> findByDepartureAndArrival(Airport departure, Airport arrival);

}
