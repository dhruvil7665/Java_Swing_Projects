/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.FlightBooking;

/**
 *
 * @author dedhi
 */
public class Passenger {
    private String passengerName;
    private String passengerPassportNumber;
    private int passengerAge;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPassportNumber() {
        return passengerPassportNumber;
    }

    public void setPassengerPassportNumber(String passengerPassportNumber) {
        this.passengerPassportNumber = passengerPassportNumber;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }
    
    public String toString(){
        return "[Name: "+this.passengerName+" ,Passport No.: "+this.passengerPassportNumber+" Age: "+this.passengerAge+"]";
    }
    
}
