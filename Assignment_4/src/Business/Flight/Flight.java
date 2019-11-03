/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Flight;

import Business.Airliner.Airliner;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author dedhi
 */
public class Flight {
    private String flightName;
    private Airliner airlinerName;
    private String aircraftName;
    private String source;
    private String destination;
    private Date departure_time;
    private Date arrival_time;
    private int flightDuration;
    //private int total_no_of_seats;
    private Date date_of_departure;
    private Date date_of_arrival;
    private float flightCost;
    private Vector<Vector<Boolean>> seats;

    public Date getDate_of_arrival() {
        return date_of_arrival;
    }

    public void setDate_of_arrival(Date date_of_arrival) {
        this.date_of_arrival = date_of_arrival;
    }

    
    public float getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(float flightCost) {
        this.flightCost = flightCost;
    }


    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Airliner getAirlinerName() {
        return airlinerName;
    }

    public void setAirlinerName(Airliner airlinerName) {
        this.airlinerName = airlinerName;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

//    public int getTotal_no_of_seats() {
//        return total_no_of_seats;
//    }
//
//    public void setTotal_no_of_seats(int total_no_of_seats) {
//        this.total_no_of_seats = total_no_of_seats;
//    }

    public Date getDate_of_departure() {
        return date_of_departure;
    }

    public void setDate_of_Departure(Date date_of_departure) {
        this.date_of_departure = date_of_departure;
    }

    public Vector getSeats() {
        return seats;
    }

    public void setSeats(Vector seats) {
        this.seats = seats;
    }
    
    public int getTotalNumOfSeats(){
        int totalNumOfSeats = 0;
        for (Object row : getSeats()) {
            for(Boolean cell : (Vector<Boolean>) row){
                if(!cell){
                    totalNumOfSeats++;
                }
            }
        }
        return totalNumOfSeats;
    }
    
   // private ZoneId dep;
    @Override
    public String toString(){
    return this.getFlightName();
    
    }
    
    
    
    
}
