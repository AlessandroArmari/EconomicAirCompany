package com.github.economicaircompany.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.model.Flight;
import com.github.economicaircompany.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PutMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.saveFlight(flight), HttpStatus.CREATED);
        // WE USE .CREATED instead of .OK to be more precise. NOT MANDATORY!
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllAirports() {
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/find/id")
    public ResponseEntity<Flight> getAirportById(@RequestParam Long id) {
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }
    // WE KEEP USING THE @RequestParam method insted of @PathVarible

    @DeleteMapping("/delete/id")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long id) {
        flightService.deleteFlightById(id);
        return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/id")
    public ResponseEntity<Flight> updateFlightById(@RequestParam Long id, @RequestBody Flight newFlight) {
        return new ResponseEntity<>(flightService.updateFlightById(id, newFlight), HttpStatus.OK);
    }

    // From now on the custom methods we created in FlightRepository.java

    @GetMapping("/find/flightCode")
    public ResponseEntity<Flight> getFlightByFlightCode(@RequestParam String flightCode) {
        return new ResponseEntity<>(flightService.getFlightByFlightCode(flightCode), HttpStatus.OK);
    }

    @GetMapping("/find/departureAndArrival")
    public ResponseEntity<List<Flight>> getFlightsByDepartureAndArrival(@RequestParam Airport departure,
            @RequestParam Airport arrival) {
        return new ResponseEntity<>(flightService.getFlightsByDepartureAndArrival(departure, arrival), HttpStatus.OK);
    }

}
