package com.github.economicaircompany.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.economicaircompany.model.Booking;
import com.github.economicaircompany.model.Flight;
import com.github.economicaircompany.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.CREATED);
        // WE USE .CREATED instead of .OK to be more precise. NOT MANDATORY!
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/find/id")
    public ResponseEntity<Booking> getBookingById(@RequestParam Long id) {
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }
    // WE KEEP USING THE @RequestParam method insted of @PathVarible

    @DeleteMapping("/delete/id")
    public ResponseEntity<String> deleteBookingById(@RequestParam Long id) {
        bookingService.deleteBookingById(id);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/id")
    public ResponseEntity<Booking> updateBookingById(@RequestParam Long id, @RequestBody Booking newBooking) {
        return new ResponseEntity<>(bookingService.updateBookingById(id, newBooking), HttpStatus.OK);
    }

    // From now on the custom methods we created in BookingRepository.java

    @GetMapping("/find/bookingCode")
    public ResponseEntity<Booking> getBookingByBookingCode(@RequestParam String bookingCode) {
        return new ResponseEntity<>(bookingService.getBookingByBookingCode(bookingCode), HttpStatus.OK);
    }

    @GetMapping("/find/flightAndPrice")
    public ResponseEntity<List<Booking>> getBookingsByFlightAndPrice(@RequestParam Flight flight,
            @RequestParam Double price) {
        return new ResponseEntity<>(bookingService.getBookingByFlightAndPrice(flight, price), HttpStatus.OK);
    }

}
