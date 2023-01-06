package com.github.economicaircompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.economicaircompany.model.Airport;

// JpaRepository has many methods we need (as create, update...)
// to work with an SQL database so we don't need to code them down

//@Repository // It's not mandatory!!!
public interface AirportRepository extends JpaRepository<Airport, Long> {
    // JpaRepository works with the java object and the id (Long)

    // Theese are custom-made methods not present in JpaRepository

    // IMPORTANT: AirportRepository is an interface so the methods in it must be
    // empty!

    /* 1 */ public Airport findByAirportCode(String airportCode);

    /* 2 */ public List<Airport> findByCountry(String country);

    /* 3 */public List<Airport> findByCountryAndCity(String country, String city);

}
