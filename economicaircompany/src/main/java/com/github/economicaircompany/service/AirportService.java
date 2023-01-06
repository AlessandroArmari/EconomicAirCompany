package com.github.economicaircompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.repository.AirportRepository;

@Service
public class AirportService {

    // This Autowired allows us to use the istance "airportRepository"
    // belonging (type AirportRepository) without creating it
    // everytime. We avoid---> AirportRepository airportRepository= new
    // AirportRepository()

    @Autowired
    AirportRepository airportRepository;

    // Here is the magic
    // Because we use airportRepository wich implements JpaRepository
    // wich has many methods "inside itself", we picked up the method
    // .save() without having written it!!!
    // Check AirportRepository.java

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll(); // Again: .findAll() method is within JpaRepository
    }

    // IMPORTANT: The pre-made method .findById(id) returns a Optional<Airport> date
    // so we use .get() in order to convert it back to Airport
    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).get();
    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirportById(Long id, Airport newAirport) {
        // Import the correct one: import java.util.Optional;
        Optional<Airport> oldAirPortOptional = airportRepository.findById(id);
        // Here we go again: the method .findById(id) returns a type
        // Optional <Airport> and we use it because tre result of the
        // "the finding" could be null--->Type Optional "can stand" null result,
        // Type Airport don't!

        // Here -infact- we're checking if the variable has an airport inside
        // OR we're checking if the line above found anything
        // If no---> goes directly to the second "return"

        if (oldAirPortOptional.isPresent()) {

            // Again: we turn a Option<Airport> to a type Airport using .get()
            // because now we need the "original version" to work with!

            Airport oldAirport = oldAirPortOptional.get();

            // Now we swap the properties of the old airport
            // with the new airports's we passed as paramether!
            oldAirport.setName(newAirport.getName());
            oldAirport.setCountry(newAirport.getCountry());
            oldAirport.setCity(newAirport.getCity());
            oldAirport.setAirportCode(newAirport.getAirportCode());
            // oldAirport.setFlights(newAirport.getFlights());
            // Actually we should update the list of Flights as well
            // but we should check if they alredy exists or not,
            // so we don't update this paramether here!

            return airportRepository.save(oldAirport);
            // and them we return the updated airport!
            // Easy?
            // ...no...
        }

        return null;

    }

    // From now on the custom methods we created in AirportRepository.java

    /* 1 */ public Airport getAirportByAirportCode(String airportCode) {
        return airportRepository.findByAirportCode(airportCode);
    }

    /* 2 */ public List<Airport> getAirportsByCountry(String country) {
        return airportRepository.findByCountry(country);
    }

    /* 3 */ public List<Airport> getAirportByCountryAndCity(String country, String city) {
        return airportRepository.findByCountryAndCity(country, city);
    }

}
