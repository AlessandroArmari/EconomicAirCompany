package com.github.economicaircompany.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Flight {

    // IMPORTING GENERATEDVALUE AND TYPE always creates
    // problems with Visual Studio Code
    // so just keep trying until it works!!!

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String flightCode;

    // Here we are:
    // many flights which have one airport as departure...
    @ManyToOne
    @JoinColumn(name = "id") // +++CONTROLLA BENE QUESTO+++

    private Airport departure;

    // ..and and one airport ad arrival
    // EACH FLIGHT: ONE airport as departure and ONE as arrival (NOT MORE!!!)
    // When you see @ManyToOne or @OneToMany we're probably
    // talking about SQL foreign keys
    // IN THIS CASE: each flights WILL HAVE 2 SQL foreign keys in its SQL TABLE!!!
    @ManyToOne
    @JoinColumn(name = "id") // +++CONTROLLA BENE QUESTO+++

    private Airport arrival;

    @Column
    private LocalDateTime departureTime;

    @Column
    private LocalDateTime arrivalTime;

    // Again: one flight--->MANY booking per flight
    @OneToMany(mappedBy = "booking") // +++CONTROLLA BENE QUESTO+++
    private List<Booking> bookings;

    public Flight() {
    }

    public Flight(String flightCode, Airport departure, Airport arrival, LocalDateTime departureTime,
            LocalDateTime arrivalTime, List<Booking> bookings) {
        this.flightCode = flightCode;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
