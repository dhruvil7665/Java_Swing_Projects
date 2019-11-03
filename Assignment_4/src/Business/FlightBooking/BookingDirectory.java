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
public class BookingDirectory {
    private ArrayList<Booking> bookingDirectory;
    
    public BookingDirectory(){
        bookingDirectory = new ArrayList<Booking>();
    }

    public ArrayList<Booking> getBookingDirectory() {
        return bookingDirectory;
    }

    public void setBookingDirectory(ArrayList<Booking> bookingDirectory) {
        this.bookingDirectory = bookingDirectory;
    }
    
    public Booking addBooking(){
        Booking b = new Booking();
        bookingDirectory.add(b);
        return b;
    }
    
}
