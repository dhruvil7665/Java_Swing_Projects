/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Abstract.User;
import Business.Airliner.Airliner;
import Business.Airliner.AirlinerDirectory;
import Business.Flight.Flight;
import Business.Flight.FlightDirectory;
import Business.User.Customer;
import Business.User.CustomerDirectory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dedhi
 */
public class ConfigurationFile {
    
    
    public ConfigurationFile(CustomerDirectory custDir, AirlinerDirectory airlinerDir, FlightDirectory flightDir) throws ParseException{
        
        ArrayList<Customer> cust = custDir.getCustomerDirectory();
        ArrayList<Flight> fl = flightDir.getFlightList();
        ArrayList<Airliner> air = airlinerDir.getAirlinerDirectory();
        
        //Customer1
        custDir.add("dedhia_dimpi@gmail.com", "Dimpi@95");
        //Customer2
        custDir.add("shah_dhruvil@gmail.com", "Dhruvil@95");
        //Customer3
        custDir.add("bajaj_rashmi@gmail.com", "Rashmi@95");
        
        //Airliner1
        Airliner a = airlinerDir.add();
        a.setAirlinerAddress("Dubai");
        a.setAirlinerName("Emirates");
        a.setAirlinerContactNumber(1245680355);
        a.setAirlinerRating((float) 4.5);
        a.setCountryOfOrigin("India");
        a.setTotalFlightsPerDay(3);
        ArrayList<String> aircraftName = new ArrayList<String>();
        aircraftName.add("Airbus A380");
        aircraftName.add("Boeing 777");
        aircraftName.add("Boeing 757");
        a.setAirlinerAircraftName(aircraftName);
        a.setYearOfEstablishment(1995);
        //Airliner2
        Airliner a2 = airlinerDir.add();
        a2.setAirlinerAddress("Qatar");
        a2.setAirlinerName("Qatar");
        a2.setAirlinerContactNumber(100000000);
        a2.setAirlinerRating((float) 4);
        a2.setCountryOfOrigin("Qatar");
        a2.setTotalFlightsPerDay(3);
        ArrayList<String> aircraftName2 = new ArrayList<String>();
        aircraftName2.add("Boeing 757");
        aircraftName2.add("Boeing 777");
        aircraftName2.add("Airbus A330");
        a2.setAirlinerAircraftName(aircraftName2);
        a2.setYearOfEstablishment(1996);
        //Airliner3
        Airliner a3 = airlinerDir.add();
        a3.setAirlinerAddress("UAE");
        a3.setAirlinerName("Ethiad");
        a3.setAirlinerContactNumber(1890245678);
        a3.setAirlinerRating((float) 3.2);
        a3.setCountryOfOrigin("UAE");
        a3.setTotalFlightsPerDay(3);
        ArrayList<String> aircraftName3 = new ArrayList<String>();
        aircraftName3.add("Airbus A330");
        aircraftName3.add("Airbus A340");
        aircraftName3.add("Boeing 757");
        a3.setAirlinerAircraftName(aircraftName3);
        a3.setYearOfEstablishment(1990);
        
        //private String[] sourceList = {"Mumbai","Boston","New York","California","Chicago","New Jersey"};
        //private String[] destinationList = {"Mumbai","Boston","New York","California","Chicago","New Jersey"};
        //Flights
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        Flight f;
        
        //Flight1 - Airliner1
        f = flightDir.createFlight();
        f.setFlightName("E1234");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(0));
        f.setSource("Boston");
        f.setDestination("New York");
        f.setDate_of_Departure(date.parse("20/10/2019"));
        f.setDate_of_arrival(date.parse("20/10/2019"));
        f.setDeparture_time(time.parse("07:00"));
        f.setArrival_time(time.parse("14:00"));
        f.setFlightDuration(7);
        f.setAircraftName(aircraftName.get(0));
        f.setFlightCost(70);
        Vector<Vector> flight11 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight11 = new Vector<Boolean>();
              vFlight11.add(false);
              vFlight11.add(true);
              vFlight11.add(true);
              vFlight11.add(false);
              vFlight11.add(false);
              vFlight11.add(true);
              flight11.add(vFlight11);
        }
        f.setSeats(flight11);
        
        //Flight2 - Airliner1
        f = flightDir.createFlight();
        f.setFlightName("A890");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(0));
        f.setSource("New York");
        f.setDestination("Boston");
        f.setDate_of_Departure(date.parse("20/10/2019"));
        f.setDate_of_arrival(date.parse("20/10/2019"));
        f.setDeparture_time(time.parse("14:00"));
        f.setArrival_time(time.parse("21:00"));
        f.setFlightDuration(7);
        f.setAircraftName(aircraftName.get(1));
        f.setFlightCost(70);
        Vector<Vector> flight12 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight12 = new Vector<Boolean>();
              vFlight12.add(false);
              vFlight12.add(true);
              vFlight12.add(false);
              vFlight12.add(true);
              vFlight12.add(true);
              vFlight12.add(true);
              flight12.add(vFlight12);
        }
        f.setSeats(flight12);
        
        //Flight3 - Airliner1
        f = flightDir.createFlight();
        f.setFlightName("G6790");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(0));
        f.setSource("California");
        f.setDestination("Chicago");
        f.setDate_of_Departure(date.parse("26/10/2019"));
        f.setDate_of_arrival(date.parse("26/10/2019"));
        f.setDeparture_time(time.parse("07:00"));
        f.setArrival_time(time.parse("11:00"));
        f.setFlightDuration(4);
        f.setAircraftName(aircraftName.get(2));
        f.setFlightCost(100);
        Vector<Vector> flight13 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight13 = new Vector<Boolean>();
              vFlight13.add(false);
              vFlight13.add(true);
              vFlight13.add(false);
              vFlight13.add(true);
              vFlight13.add(true);
              vFlight13.add(false);
              flight13.add(vFlight13);
        }
        f.setSeats(flight13);
        
        //Flight1 - Airliner2
        f = flightDir.createFlight();
        f.setFlightName("D147");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(1));
        f.setSource("Chicago");
        f.setDestination("New York");
        f.setDate_of_Departure(date.parse("25/10/2019"));
        f.setDate_of_arrival(date.parse("25/10/2019"));
        f.setDeparture_time(time.parse("01:00"));
        f.setArrival_time(time.parse("06:00"));
        f.setFlightDuration(5);
        f.setAircraftName(aircraftName.get(0));
        f.setFlightCost(70);
        Vector<Vector> flight21 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight21 = new Vector<Boolean>();
              vFlight21.add(true);
              vFlight21.add(true);
              vFlight21.add(false);
              vFlight21.add(true);
              vFlight21.add(true);
              vFlight21.add(false);
              flight21.add(vFlight21);
        }
        f.setSeats(flight21);
        
        //Flight2 - Airliner2
        f = flightDir.createFlight();
        f.setFlightName("F280");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(1));
        f.setSource("Boston");
        f.setDestination("Chicago");
        f.setDate_of_Departure(date.parse("25/10/2019"));
        f.setDate_of_arrival(date.parse("25/10/2019"));
        f.setDeparture_time(time.parse("05:00"));
        f.setArrival_time(time.parse("11:00"));
        f.setFlightDuration(6);
        f.setAircraftName(aircraftName.get(1));
        f.setFlightCost((float) 85.55);
        Vector<Vector> flight22 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight22 = new Vector<Boolean>();
              vFlight22.add(true);
              vFlight22.add(true);
              vFlight22.add(false);
              vFlight22.add(true);
              vFlight22.add(true);
              vFlight22.add(false);
              flight22.add(vFlight22);
        }
        f.setSeats(flight22);
        
        //Flight3 - Airliner2
        f = flightDir.createFlight();
        f.setFlightName("V480");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(1));
        f.setSource("New Jersey");
        f.setDestination("New York");
        f.setDate_of_Departure(date.parse("24/10/2019"));
        f.setDate_of_arrival(date.parse("24/10/2019"));
        f.setDeparture_time(time.parse("04:00"));
        f.setArrival_time(time.parse("07:00"));
        f.setFlightDuration(3);
        f.setAircraftName(aircraftName.get(2));
        f.setFlightCost(50);
        Vector<Vector> flight23 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight23 = new Vector<Boolean>();
              vFlight23.add(true);
              vFlight23.add(true);
              vFlight23.add(false);
              vFlight23.add(true);
              vFlight23.add(true);
              vFlight23.add(false);
              flight23.add(vFlight23);
        }
        f.setSeats(flight23);
        
        //Flight1 - Airliner3
        f = flightDir.createFlight();
        f.setFlightName("F245");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(2));
        f.setSource("California");
        f.setDestination("New Jersey");
        f.setDate_of_Departure(date.parse("22/10/2019"));
        f.setDate_of_arrival(date.parse("22/10/2019"));
        f.setDeparture_time(time.parse("15:00"));
        f.setArrival_time(time.parse("19:00"));
        f.setFlightDuration(4);
        f.setAircraftName(aircraftName.get(0));
        f.setFlightCost((float) 55.5);
        Vector<Vector> flight31 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight31 = new Vector<Boolean>();
              vFlight31.add(true);
              vFlight31.add(true);
              vFlight31.add(false);
              vFlight31.add(true);
              vFlight31.add(true);
              vFlight31.add(false);
              flight31.add(vFlight31);
        }
        f.setSeats(flight31);
        
        //Flight2 - Airliner3
        f = flightDir.createFlight();
        f.setFlightName("K289");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(2));
        f.setSource("Boston");
        f.setDestination("New Jersey");
        f.setDate_of_Departure(date.parse("01/11/2019"));
        f.setDate_of_arrival(date.parse("01/11/2019"));
        f.setDeparture_time(time.parse("09:00"));
        f.setArrival_time(time.parse("13:00"));
        f.setFlightDuration(4);
        f.setAircraftName(aircraftName.get(1));
        f.setFlightCost((float) 90);
        Vector<Vector> flight32 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight32 = new Vector<Boolean>();
              vFlight32.add(true);
              vFlight32.add(true);
              vFlight32.add(false);
              vFlight32.add(true);
              vFlight32.add(true);
              vFlight32.add(false);
              flight32.add(vFlight32);
        }
        f.setSeats(flight32);
        
        //Flight3 - Airliner3
        f = flightDir.createFlight();
        f.setFlightName("L945");
        f.setAirlinerName(airlinerDir.getAirlinerDirectory().get(2));
        f.setSource("California");
        f.setDestination("New Jersey");
        f.setDate_of_Departure(date.parse("29/10/2019"));
        f.setDate_of_arrival(date.parse("29/10/2019"));
        f.setDeparture_time(time.parse("02:00"));
        f.setArrival_time(time.parse("07:00"));
        f.setFlightDuration(5);
        f.setAircraftName(aircraftName.get(2));
        f.setFlightCost((float) 64.99);
        Vector<Vector> flight33 = new Vector<Vector>();
        for(int i =0 ; i<25;i++){
              Vector<Boolean> vFlight33 = new Vector<Boolean>();
              vFlight33.add(true);
              vFlight33.add(true);
              vFlight33.add(false);
              vFlight33.add(true);
              vFlight33.add(true);
              vFlight33.add(false);
              flight33.add(vFlight33);
        }
        f.setSeats(flight33);
 
    }
    
    
}
