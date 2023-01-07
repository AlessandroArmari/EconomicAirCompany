package com.github.economicaircompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.economicaircompany.model.Booking;
import com.github.economicaircompany.model.Flight;
import com.github.economicaircompany.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightService flightService;

    public Booking saveBooking(Booking booking) {

        // FOR EXPLANATION: GO TO FlightService.java
        if (booking.getFlight() != null) {
            flightService.saveFlight(booking.getFlight());
        }

        return bookingRepository.save(booking);

    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).get();
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking updateBookingById(Long id, Booking newBooking) {
        Optional<Booking> oldBookingOptional = bookingRepository.findById(id);

        if (oldBookingOptional.isPresent()) {

            Booking oldBooking = oldBookingOptional.get();

            oldBooking.setBookingCode(newBooking.getBookingCode());
            oldBooking.setSeatDescription(newBooking.getSeatDescription());
            oldBooking.setPrice(newBooking.getPrice());
            oldBooking.setFlight(newBooking.getFlight()); // We should check if it exists

            return bookingRepository.save(oldBooking);
        }

        return null;
    }

    // From now on the custom methods we created in AirportRepository.java

    /* 1 */
    public Booking getBookingByBookingCode(String bookingCode) {
        return bookingRepository.findByBookingCode(bookingCode);
    }

    /* 2 */
    public List<Booking> getBookingByFlightAndPrice(Flight flight, Double price) {
        return bookingRepository.findByFlightAndPrice(flight, price);
    }

}
