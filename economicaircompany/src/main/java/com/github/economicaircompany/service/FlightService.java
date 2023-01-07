package com.github.economicaircompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.model.Flight;
import com.github.economicaircompany.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportService airportService; // we will need airportService shortly...

    public Flight saveFlight(Flight flight) {

        // FIRST: we check we're passing flight which
        // has different departure and arrival
        if (flight.getDeparture() != flight.getArrival()) {

            /*
             * IMPORTANT: because we know departure and arrival in flight
             * table are 2 foreign keys linked to Airport table
             * WE MUST BE SURE these airports already exists--->
             * --->So, se pick up departure and arrival and we salve
             * them using .saveAirport method stored in "AirportService"
             * ALL THIS in order to avoid the common SQL database
             * error: when a foreign key refers to an not existing "Id"
             * in another table
             */
            if (flight.getDeparture() != null) {
                airportService.saveAirport(flight.getDeparture());
            }
            if (flight.getArrival() != null) {
                airportService.saveAirport(flight.getArrival());
            }

            // now that we are sure the two foreign keys can refer to
            // two existing airports, we can save the flight!
            return flightRepository.save(flight);

        }

        return null;

    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll(); // AGAIN: a default method store in JpaRepository
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).get(); // AGAIN: .get() to re-convert an Optional<Flight> to Flight
    }

    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight updateFlightById(Long id, Flight newFlight) {

        Optional<Flight> oldFlightOptional = flightRepository.findById(id);

        // EXPLANATION: GO TO AirportService.java!!!

        if (oldFlightOptional.isPresent()) {

            Flight oldFlight = oldFlightOptional.get();

            oldFlight.setFlightCode(newFlight.getFlightCode());
            oldFlight.setDeparture(newFlight.getDeparture()); // AGAIN: WE SHOULD CHECK IF THIS AIRPORT EXISTS!!
            oldFlight.setArrival(newFlight.getArrival()); // AGAIN: WE SHOULD CHECK IF THIS AIRPORT EXISTS!!!
            oldFlight.setDepartureTime(newFlight.getDepartureTime());
            oldFlight.setArrivalTime(newFlight.getArrivalTime());
            oldFlight.setBookings(newFlight.getBookings()); // we should check the bookings as well!!!

            return flightRepository.save(oldFlight);
        }

        return null;

    }

    // From now on the custom methods we created in FlightRepository.java

    /* 1 */
    public Flight getFlightByFlightCode(String flightCode) {
        return flightRepository.findByFlightCode(flightCode);
    }

    /* 2 */
    public List<Flight> getFlightsByDepartureAndArrival(Airport departure, Airport arrival) {
        return flightRepository.findByDepartureAndArrival(departure, arrival);
    }

}
