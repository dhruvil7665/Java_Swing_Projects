/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Trek;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class TrekSlotAndCost {
    private Date trekStartDate;
    private float trekTravelCost;
    private float trekAccomodationCost;
    private float trekMealCost;
    private float trekMedicalEmergency;
    private float trekTax;
    private float trekTotalCost; 
    private int totalSeats;
    private int availableSeats;

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getTrekStartDate() {
        return trekStartDate;
    }

    public void setTrekStartDate(Date trekStartDate) {
        this.trekStartDate = trekStartDate;
    }

    public float getTrekTravelCost() {
        return trekTravelCost;
    }

    public void setTrekTravelCost(float trekTravelCost) {
        this.trekTravelCost = trekTravelCost;
    }

    public float getTrekAccomodationCost() {
        return trekAccomodationCost;
    }

    public void setTrekAccomodationCost(float trekAccomodationCost) {
        this.trekAccomodationCost = trekAccomodationCost;
    }

    public float getTrekMealCost() {
        return trekMealCost;
    }

    public void setTrekMealCost(float trekMealCost) {
        this.trekMealCost = trekMealCost;
    }

    public float getTrekMedicalEmergency() {
        return trekMedicalEmergency;
    }

    public void setTrekMedicalEmergency(float trekMedicalEmergency) {
        this.trekMedicalEmergency = trekMedicalEmergency;
    }

    public float getTrekTax() {
        return trekTax;
    }

    public void setTrekTax(float trekTax) {
        this.trekTax = trekTax;
    }

    public float getTrekTotalCost() {
        return trekTotalCost;
    }

    public void setTrekTotalCost(float trekTotalCost) {
        this.trekTotalCost = trekTotalCost;
    }
    
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(trekStartDate == null){
            return "Slots not added";
        }else{
        return dateFormat.format(trekStartDate);
        }
    }
    
}
