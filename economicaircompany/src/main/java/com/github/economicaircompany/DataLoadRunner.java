package com.github.economicaircompany;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.economicaircompany.model.Airport;
import com.github.economicaircompany.model.Flight;
import com.github.economicaircompany.model.Booking;

import com.github.economicaircompany.service.AirportService;
import com.github.economicaircompany.service.BookingService;
import com.github.economicaircompany.service.FlightService;

//

@Component
public class DataLoadRunner implements CommandLineRunner {

        @Autowired
        AirportService airportService;

        @Autowired
        FlightService flightService;

        @Autowired
        BookingService bookingService;

        @Override
        public void run(String... args) throws Exception {

                // HERE WE CREATE JAVA OBJECT TO BE CASTED IN SQL
                // AS WE LAUNCH APP.JAVA
                // YOU CAN STILL USE the PostMan Method /save
                // IT'S UP TO YOU!
                // BUT THIS METHOD IS FASTER AND ALLOWSYOU TO EMPTY AND RE-FILL YOUR
                // SQL DATABASE QUICKLY!!!

                // +++VERY IMPORTANT: NOW EVERYTIME YOU LAUNCH THE APP NEW LINES WILL BE
                // CREATED!!!

                Airport fiumicino = new Airport("Fiumicino", "Italy", "Rome", "FCO", null);
                Airport malpensa = new Airport("Malpensa", "Italy", "Milan", "MXP", null);
                Airport charlesDeGaulle = new Airport("Charles_De_Gaulle", "France", "Paris", "CDG", null);
                Airport heathrow = new Airport("Heathrow", "Uk", "London", "LHR", null);

                // we use the same pattern se did in AirportService and PostMan
                // But without using the command create in Postman!

                airportService.saveAirport(fiumicino);
                airportService.saveAirport(malpensa);
                airportService.saveAirport(charlesDeGaulle);
                airportService.saveAirport(heathrow);

                // WE PASS PARAMETHERS AS JAVA ITEMS (LOOK ARRIVAL, DEPARTURE AND DATE!!!)
                // Bookings paramether is empty because it isn't present as Column in SQL Table
                // "Flight"
                Flight flight1 = new Flight("xxxxx1", heathrow, fiumicino, LocalDateTime.of(2022, 11, 11, 12, 30),
                                LocalDateTime.of(2022, 11, 11, 15, 00), null);

                Flight flight2 = new Flight("yyyyy2", charlesDeGaulle, malpensa, LocalDateTime.of(2022, 11, 30, 19, 00),
                                LocalDateTime.of(2022, 11, 30, 21, 00), null);

                Flight flight3 = new Flight("zzzzz3", malpensa, fiumicino, LocalDateTime.of(2021, 10, 10, 12, 00),
                                LocalDateTime.of(2022, 10, 10, 14, 27), null);

                Flight flight4 = new Flight("ttttt4", fiumicino, heathrow, LocalDateTime.of(2020, 5, 5, 15, 00),
                                LocalDateTime.of(2020, 5, 5, 17, 00), null);

                flightService.saveFlight(flight1);
                flightService.saveFlight(flight2);
                flightService.saveFlight(flight3);
                flightService.saveFlight(flight4);

                Booking booking1 = new Booking("UYT10", "aisle", 15, flight1);
                Booking booking2 = new Booking("UYT11", "aisle", 25, flight1);
                Booking booking3 = new Booking("UYT25", "window", 25, flight1);
                Booking booking4 = new Booking("W5127", "window", 44, flight2);
                Booking booking5 = new Booking("W5128", "window", 46, flight2);
                Booking booking6 = new Booking("T714X", "aisle", 13.5, flight3);

                bookingService.saveBooking(booking1);
                bookingService.saveBooking(booking2);
                bookingService.saveBooking(booking3);
                bookingService.saveBooking(booking4);
                bookingService.saveBooking(booking5);
                bookingService.saveBooking(booking6);

                /*
                 * ANOTHER TECNIQUE:
                 * USING A CYCLE TO CREATE FAC-SIMILE LINES IN SQL
                 * +++USE IT WISELY BECAUSE THE VALUES WILL BE SOMETHING LIKE:
                 * 
                 * -Name1, Country1, City1, AirportCode1
                 * -Name2, Country2, City2, AirportCode2
                 * -Name3, Country3, City3, AirportCode3
                 */

                /*
                 * Airport temp;
                 * 
                 * for(int i=0;i<20;i++){
                 * temp=new Airport("Name "+i, "Country "+i, "City "+i,"AirportCode "+i);
                 * 
                 * airportService.saveAirport(temp);
                 * }
                 */
        }
}
