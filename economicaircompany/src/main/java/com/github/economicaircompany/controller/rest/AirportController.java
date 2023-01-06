package com.github.economicaircompany.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.service.AirportService;

@RestController
@RequestMapping("/airport") // the ending of the url we type on a browser
public class AirportController {

    @Autowired
    AirportService airportService;

    // this command request a "Body", an object that we will pass the system by
    // Postman.
    // The body is the airport that will be send and stored to SQL

    @PostMapping("/create") // ---> this command is a post
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.saveAirport(airport), HttpStatus.CREATED);
    }

    @GetMapping("/all") // ---> this command is a get
    public ResponseEntity<List<Airport>> getAllAirports() { // --->here we don't need a paramether
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    // FIRST METHOD TO DO FIND-BY-ID: @RequestParam (THE BEST METHOD)

    @GetMapping("/find/id") // @RequestParam---> this command requires a Param we will pas by PostMan
    public ResponseEntity<Airport> getAirportByIdByRequestParam(@RequestParam Long id) { // --->wewill pass this id
        return new ResponseEntity<>(airportService.getAirportById(id), HttpStatus.OK);
    }

    // SECOND METHOD TO DO FIND-BY-ID: @PathVariable
    @GetMapping("/find/{id}") // --->the "id" between curly braces is linked to @PathVariable
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return new ResponseEntity<>(airportService.getAirportById(id), HttpStatus.OK);

        // --->this "id" is filled by the id we chose in the curly braces!
    }

    @DeleteMapping("/delete") // +++FROM NOW ON WE USE ONLY THE @RequestParam method
    public ResponseEntity<String> deleteAirportByIdRequestParam(@RequestParam Long id) {

        airportService.deleteAirportById(id); // Here we just deleted the airport with a certain id
        return new ResponseEntity<>("Airport deleted successfully", HttpStatus.OK);
        // Here we return a String as required by the return-type of the function
        // ResponseEntity is similar to a "package" wich contains the Web Page we send
        // to the client when they type on the browser the URL "/delete"
        // In this case is just a String, but it could've been videos, images...
    }

    // AGAIN: WE USE ONLY @RequestParam ofr the id
    @PutMapping("/update/id") // updating is a PUT command
    public ResponseEntity<Airport> updateAirportByIdRequestParam(@RequestParam Long id,
            @RequestBody Airport newAirport) {
        return new ResponseEntity<>(airportService.updateAirportById(id, newAirport), HttpStatus.OK);
    }
    // TO DO THIS WE NEED TO PASS 2 DIFFERENT THINGS:
    // 1)Long id (@RequestParam)
    // 2) Airport newAirport (@RequestBody)

    // From now on the custom methods we created in AirportRepository.java

    @GetMapping("/find/airportCode")
    public ResponseEntity<Airport> getAirportByAirportCodeRequestParam(@RequestParam String airportCode) {
        return new ResponseEntity<>(airportService.getAirportByAirportCode(airportCode), HttpStatus.OK);
    }

    @GetMapping("/find/country") // List because could be more than 1 airport!!!
    public ResponseEntity<List<Airport>> getAirportsByCountryRequestParam(@RequestParam String country) {
        return new ResponseEntity<>(airportService.getAirportsByCountry(country), HttpStatus.OK);
    }

    @GetMapping("/find/countryAndCity") // List because could be more than 1 airport!!!
    public ResponseEntity<List<Airport>> getAirportsByCountryAndCity(@RequestParam String country,
            @RequestParam String city) {
        return new ResponseEntity<>(airportService.getAirportByCountryAndCity(country, city), HttpStatus.OK);
    }

}
