/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.FlightBooking;

import Business.Flight.Flight;
import Business.User.Customer;

/**
 *
 * @author dedhi
 */
public class Booking {
    private Customer customer;
    private Flight flight;
    private int totalNumOfSeatsBooked;
    private PassengerDirectory passengerDirectory;
    private float totalCost;

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getTotalNumOfSeatsBooked() {
        return totalNumOfSeatsBooked;
    }

    public void setTotalNumOfSeatsBooked(int totalNumOfSeatsBooked) {
        this.totalNumOfSeatsBooked = totalNumOfSeatsBooked;
    }

    public PassengerDirectory getPassengerDirectory() {
        return passengerDirectory;
    }

    public void setPassengerDirectory(PassengerDirectory passengerDirectory) {
        this.passengerDirectory = passengerDirectory;
    }
    
    public String toString(){
        return this.customer.getUserName();
    }
    
}
