/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Airliner;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class Airliner {
    private String airlinerName;
    private String airlinerAddress;
    private double airlinerContactNumber;
    private int totalFlightsPerDay;
    private float airlinerRating;
    private String countryOfOrigin;
    private int yearOfEstablishment;
    private ArrayList<String> airlinerAircraftName;
    
    /*public Airliner(String airlinerName, String airlinerAddress, long airlinerContactNumber, int totalFlightsPerDay, float airlinerRating, String countryOfOrigin, Date dateOfEstablishment, String airlinerAircraftName)
    {
            this.airlinerName=airlinerName;
            this.airlinerAddress=airlinerAddress;
            this.airlinerContactNumber=airlinerContactNumber;
            this.totalFlightsPerDay=totalFlightsPerDay;
            this.airlinerRating=airlinerRating;
            this.countryOfOrigin=countryOfOrigin;
            this.dateOfEstablishment=dateOfEstablishment;
            this.airlinerAircraftName=airlinerAircraftName;
           
    }*/
    
  
    
    public String getAirlinerName() {
        return airlinerName;
    }

    public void setAirlinerName(String airlinerName) {
        this.airlinerName = airlinerName;
    }

    public String getAirlinerAddress() {
        return airlinerAddress;
    }

    public void setAirlinerAddress(String airlinerAddress) {
        this.airlinerAddress = airlinerAddress;
    }

    public String getAirlinerContactNumber() {
        return String.format("%.0f",airlinerContactNumber);
    }

    public void setAirlinerContactNumber(double airlinerContactNumber) {
        this.airlinerContactNumber = airlinerContactNumber;
    }

    public int getTotalFlightsPerDay() {
        return totalFlightsPerDay;
    }

    public void setTotalFlightsPerDay(int totalFlightsPerDay) {
        this.totalFlightsPerDay = totalFlightsPerDay;
    }

    public float getAirlinerRating() {
        return airlinerRating;
    }

    public void setAirlinerRating(float airlinerRating) {
        this.airlinerRating = airlinerRating;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(int yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public ArrayList<String> getAirlinerAircraftName() {
        return airlinerAircraftName;
    }

    public void setAirlinerAircraftName(ArrayList<String> airlinerAircraftName) {
        this.airlinerAircraftName = airlinerAircraftName;
    }
    
    
    @Override
    public String toString(){
        return this.airlinerName;
    }
}
