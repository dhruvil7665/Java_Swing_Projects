/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.FlightBooking;

import java.util.ArrayList;

/**
 *
 * @author dedhi
 */
public class PassengerDirectory {
    private ArrayList<Passenger> passengerDirectory;

    public PassengerDirectory(){
        passengerDirectory = new ArrayList<Passenger>();
    }
    public ArrayList<Passenger> getPassengerDirectory() {
        return passengerDirectory;
    }

    public void setPassengerDirectory(ArrayList<Passenger> passengerDirectory) {
        this.passengerDirectory = passengerDirectory;
    }
    
    public Passenger addPassenger(){
        Passenger p = new Passenger();
        passengerDirectory.add(p);
        return p;
    }
    
    
}
