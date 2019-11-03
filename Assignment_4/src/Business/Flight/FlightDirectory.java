/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Flight;

import java.util.ArrayList;

/**
 *
 * @author dedhi
 */
public class FlightDirectory {
    
    
     private ArrayList<Flight> flightList;
     
     public FlightDirectory(){
        flightList = new ArrayList<>();
        }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(ArrayList<Flight> flightList) {
        this.flightList = flightList;
    }
     
      
    public Flight createFlight(){
        Flight flight = new Flight();
        //flight.setFlightName(name);
        flightList.add(flight);
        return flight;
    }
   
    
    public void removeFlight(Flight flight){
        flightList.remove(flight);
        
    }
    
    
    
}
