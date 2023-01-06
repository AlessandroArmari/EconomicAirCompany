package com.github.economicaircompany.model;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String bookingCode;

    @Column
    private String seatDescription;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "id") // +++CONTROLLA BENE QUESTO+++
    private Flight flight;

    public Booking() {
    }

    public Booking(String bookingCode, String seatDescription, double price, Flight flight) {
        this.bookingCode = bookingCode;
        this.seatDescription = seatDescription;
        this.price = price;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getSeatDescription() {
        return seatDescription;
    }

    public void setSeatDescription(String seatDescription) {
        this.seatDescription = seatDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
